package com.xjd.wechat.escrow.callback;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.xjd.wechat.ApiException;
import com.xjd.wechat.escrow.callback.auth.*;
import com.xjd.wechat.escrow.callback.component.ComponentListener;
import com.xjd.wechat.escrow.callback.component.ComponentVerifyTicket;

/**
 * @author elvis.xu
 * @since 2018-04-12 14:31
 */
public class Dispatcher {
	protected List<Listener> listenerList = new ArrayList<>();

	public void addListener(Listener... listeners) {
		if (listeners == null) {
			return;
		}
		for (Listener listener : listeners) {
			this.listenerList.add(listener);
		}
	}

	public void removeListener(Listener... listeners) {
		if (listeners == null) {
			return;
		}
		for (Listener listener : listeners) {
			this.listenerList.remove(listener);
		}
	}

	public void clearListener() {
		this.listenerList.clear();
	}

	public List<Listener> getListeners() {
		return listenerList;
	}

	public void dispatch(String xml) {
		Document document = getDocument(xml);

		BaseMsg.InfoType type = BaseMsg.InfoType.ofCode(parseValue(document, "//InfoType", String.class));
		if (type == null) {
			throw new ApiException(null, "Unknown 'InfoType': " + xml);
		}

		switch (type) {
		case COMPONENT_VERIFY_TICKET:
			processComponentVerifyTicket(document);
			break;
		case AUTHORIZED:
			processAuthorized(document);
			break;
		case UNAUTHORIZED:
			processUnauthorized(document);
			break;
		case UPDATE_AUTHORIZED:
			processUpdateAuthorized(document);
			break;
		}

	}

	protected void processComponentVerifyTicket(Document document) {
		ComponentVerifyTicket msg = new ComponentVerifyTicket()
				.setComponentVerifyTicket(parseValue(document, "//ComponentVerifyTicket", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		listenerList.stream().forEach(l -> {
			if (l instanceof ComponentListener) {
				((ComponentListener) l).onComponentVerifyTicket(msg, context);
			}
		});
	}

	protected void processAuthorized(Document document) {
		Authorized msg = new Authorized()
				.setAuthorizationCode(parseValue(document, "//AuthorizationCode", String.class))
				.setAuthorizationCodeExpiredTime(parseValue(document, "//AuthorizationCodeExpiredTime", Long.class))
				.setPreAuthCode(parseValue(document, "//PreAuthCode", String.class));
		parseAuth(document, msg);

		Context context = new Context();
		listenerList.stream().forEach(l -> {
			if (l instanceof AuthListener) {
				((AuthListener) l).onAuthorized(msg, context);
			}
		});
	}

	protected void processUnauthorized(Document document) {
		Unauthorized msg = new Unauthorized();
		parseAuth(document, msg);

		Context context = new Context();
		listenerList.stream().forEach(l -> {
			if (l instanceof AuthListener) {
				((AuthListener) l).onUnauthorized(msg, context);
			}
		});
	}

	protected void processUpdateAuthorized(Document document) {
		UpdateAuthorized msg = new UpdateAuthorized()
				.setAuthorizationCode(parseValue(document, "//AuthorizationCode", String.class))
				.setAuthorizationCodeExpiredTime(parseValue(document, "//AuthorizationCodeExpiredTime", Long.class))
				.setPreAuthCode(parseValue(document, "//PreAuthCode", String.class));
		parseAuth(document, msg);

		Context context = new Context();
		listenerList.stream().forEach(l -> {
			if (l instanceof AuthListener) {
				((AuthListener) l).onUpdateAuthorized(msg, context);
			}
		});
	}

	protected void parseAuth(Document document, BaseAuth msg) {
		msg.setAuthorizerAppId(parseValue(document, "//AuthorizerAppid", String.class));
		parseGeneral(document, msg);
	}

	protected void parseGeneral(Document document, BaseMsg msg) {
		msg.setAppId(parseValue(document, "//AppId", String.class));
		msg.setCreateTime(parseValue(document, "//CreateTime", Long.class));
	}

	protected Document getDocument(String xml) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(new StringReader(xml));
		} catch (DocumentException e) {
			throw new ApiException(e, "parse xml failed: " + xml);
		}
		return document;
	}

	protected <T> T parseValue(Document document, String xpath, Class<T> clazz) {
		Node node = document.selectSingleNode(xpath);
		if (node == null) {
			return null;
		}
		String s = node.getStringValue();
		if (String.class.equals(clazz)) {
			return (T) s;
		}
		if (Long.class.equals(clazz)) {
			return (T) Long.valueOf(s);
		}
		if (Integer.class.equals(clazz)) {
			return (T) Integer.valueOf(s);
		}
		if (BigDecimal.class.equals(clazz)) {
			return (T) new BigDecimal(s);
		}
		return null;
	}

	public static interface Listener {
	}

	public static class Context extends HashMap {
	}
}
