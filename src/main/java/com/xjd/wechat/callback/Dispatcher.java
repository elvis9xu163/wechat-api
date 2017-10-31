package com.xjd.wechat.callback;

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
import com.xjd.wechat.callback.common.*;
import com.xjd.wechat.callback.event.*;

/**
 * @author elvis.xu
 * @since 2017-10-30 16:59
 */
public class Dispatcher {

	protected List<Listener> listenerList = new ArrayList<>();

	public void addListener(Listener... listeners) {
		if (listeners == null) return;
		for (Listener listener : listeners) {
			this.listenerList.add(listener);
		}
	}

	public void removeListener(Listener... listeners) {
		if (listeners == null) return;
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

		BaseMsg.MsgType msgType = BaseMsg.MsgType.ofCode(parseValue(document, "//MsgType", String.class));
		if (msgType == null) {
			throw new ApiException(null, "Unknown 'MsgType': " + xml);
		}

		switch (msgType) {
		case TEXT:
			processText(document);
			break;
		case IMAGE:
			processImage(document);
			break;
		case VOICE:
			processVoice(document);
			break;
		case VIDEO:
			processVideo(document);
			break;
		case SHORT_VIDEO:
			processShortVideo(document);
			break;
		case LOCATION:
			processLocation(document);
			break;
		case LINK:
			processLink(document);
			break;
		case EVENT:
			BaseEvent.Event event = BaseEvent.Event.ofCode(parseValue(document, "//Event", String.class));
			if (event == null) {
				throw new ApiException(null, "Unknown 'Event': " + xml);
			}
			switch (event) {
			case SUBSCRIBE:
				processEventSubscribe(document);
				break;
			case UNSUBSCRIBE:
				processEventUnsubscribe(document);
				break;
			case SCAN:
				processEventScan(document);
				break;
			case LOCATION:
				processEventLocation(document);
				break;
			case CLICK:
				processEventClick(document);
				break;
			case TEMPLATE_SEND_JOB_FINISH:
				processEventTemplateSendJobFinish(document);
				break;
			}
			break;
		}
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

	protected void parseGeneral(Document document, BaseMsg baseMsg) {
		baseMsg.setToUserName(parseValue(document, "//ToUserName", String.class))
				.setFromUserName(parseValue(document, "//FromUserName", String.class))
				.setCreateTime(parseValue(document, "//CreateTime", Long.class));
		if (baseMsg instanceof BaseCommon) {
			((BaseCommon) baseMsg).setMsgId(parseValue(document, "//MsgId", Long.class));
		}
	}

	protected void processText(Document document) {
		Text msg = new Text().setContent(parseValue(document, "//Content", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof CommonListener) {
				((CommonListener) listener).onText(msg, context);
			}
		}
	}

	protected void processImage(Document document) {
		Image msg = new Image()
				.setPicUrl(parseValue(document, "//PicUrl", String.class))
				.setMediaId(parseValue(document, "//MediaId", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof CommonListener) {
				((CommonListener) listener).onImage(msg, context);
			}
		}
	}

	protected void processVoice(Document document) {
		Voice msg = new Voice()
				.setMediaId(parseValue(document, "//MediaId", String.class))
				.setFormat(parseValue(document, "//Format", String.class))
				.setRecognition(parseValue(document, "//Recognition", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof CommonListener) {
				((CommonListener) listener).onVoice(msg, context);
			}
		}
	}

	protected void processVideo(Document document) {
		Video msg = new Video()
				.setMediaId(parseValue(document, "//MediaId", String.class))
				.setThumbMediaId(parseValue(document, "//ThumbMediaId", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof CommonListener) {
				((CommonListener) listener).onVideo(msg, context);
			}
		}
	}

	protected void processShortVideo(Document document) {
		ShortVideo msg = new ShortVideo()
				.setMediaId(parseValue(document, "//MediaId", String.class))
				.setThumbMediaId(parseValue(document, "//ThumbMediaId", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof CommonListener) {
				((CommonListener) listener).onShortVideo(msg, context);
			}
		}
	}

	protected void processLocation(Document document) {
		Location msg = new Location()
				.setLocationX(parseValue(document, "//Location_X", BigDecimal.class))
				.setLocationY(parseValue(document, "//Location_Y", BigDecimal.class))
				.setScale(parseValue(document, "//Scale", BigDecimal.class))
				.setLabel(parseValue(document, "//Label", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof CommonListener) {
				((CommonListener) listener).onLocation(msg, context);
			}
		}
	}

	protected void processLink(Document document) {
		Link msg = new Link()
				.setTitle(parseValue(document, "//Title", String.class))
				.setDescription(parseValue(document, "//Description", String.class))
				.setUrl(parseValue(document, "//Url", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof CommonListener) {
				((CommonListener) listener).onLink(msg, context);
			}
		}
	}

	protected void processEventSubscribe(Document document) {
		Subscribe msg = new Subscribe()
				.setEventKey(parseValue(document, "//EventKey", String.class))
				.setTicket(parseValue(document, "//Ticket", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof EventListener) {
				((EventListener) listener).onSubscribe(msg, context);
			}
		}
	}

	protected void processEventUnsubscribe(Document document) {
		Unsubscribe msg = new Unsubscribe();
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof EventListener) {
				((EventListener) listener).onUnsubscribe(msg, context);
			}
		}
	}

	protected void processEventScan(Document document) {
		Scan msg = new Scan()
				.setEventKey(parseValue(document, "//EventKey", String.class))
				.setTicket(parseValue(document, "//Ticket", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof EventListener) {
				((EventListener) listener).onScan(msg, context);
			}
		}
	}

	protected void processEventLocation(Document document) {
		LocationEvent msg = new LocationEvent()
				.setLatitude(parseValue(document, "//Latitude", BigDecimal.class))
				.setLongitude(parseValue(document, "//Longitude", BigDecimal.class))
				.setPrecision(parseValue(document, "//Precision", BigDecimal.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof EventListener) {
				((EventListener) listener).onLocation(msg, context);
			}
		}
	}

	protected void processEventClick(Document document) {
		Click msg = new Click()
				.setEventKey(parseValue(document, "//EventKey", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof EventListener) {
				((EventListener) listener).onClick(msg, context);
			}
		}
	}

	protected void processEventTemplateSendJobFinish(Document document) {
		TemplateSendJobFinish msg = new TemplateSendJobFinish()
				.setMsgID(parseValue(document, "//MsgID", Long.class))
				.setStatus(parseValue(document, "//Status", String.class));
		parseGeneral(document, msg);

		Context context = new Context();
		for (Listener listener : listenerList) {
			if (listener instanceof EventListener) {
				((EventListener) listener).onTemplateSendJobFinish(msg, context);
			}
		}
	}

	protected <T> T parseValue(Document document, String xpath, Class<T> clazz) {
		Node node = document.selectSingleNode(xpath);
		if (node == null) return null;
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
