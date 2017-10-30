package com.xjd.wechat.callback;

import java.util.Map;

import org.junit.Test;

import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.callback.common.*;
import com.xjd.wechat.callback.event.Scan;
import com.xjd.wechat.callback.event.Subscribe;
import com.xjd.wechat.callback.event.TemplateSendJobFinish;

/**
 * @author elvis.xu
 * @since 2017-10-30 19:24
 */
public class DispatcherTest {

	@Test
	public void test() throws Exception {

		Dispatcher dispatcher = new Dispatcher();
		dispatcher.addListener(new ListenerAdapter() {
			@Override
			public void onSubscribe(Subscribe entity, Map context) {
				System.out.println(ApiUtils.toJson(entity));
			}

			@Override
			public void onText(Text entity, Map context) {
				System.out.println(ApiUtils.toJson(entity));
			}

			@Override
			public void onTemplateSendJobFinish(TemplateSendJobFinish entity, Map context) {
				System.out.println(ApiUtils.toJson(entity));
			}

			@Override
			public void onScan(Scan msg, Map context) {
				System.out.println(ApiUtils.toJson(msg));
			}

			@Override
			public void onImage(Image msg, Map context) {
				System.out.println(ApiUtils.toJson(msg));
			}

			@Override
			public void onVoice(Voice msg, Map context) {
				System.out.println(ApiUtils.toJson(msg));
			}

			@Override
			public void onVideo(Video msg, Map context) {
				System.out.println(ApiUtils.toJson(msg));
			}

			@Override
			public void onShortVideo(ShortVideo msg, Map context) {
				System.out.println(ApiUtils.toJson(msg));
			}

			@Override
			public void onLocation(Location msg, Map context) {
				System.out.println(ApiUtils.toJson(msg));
			}

			@Override
			public void onLink(Link msg, Map context) {
				System.out.println(ApiUtils.toJson(msg));
			}
		});

		dispatcher.dispatch("           <xml>\n" +
				"           <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>\n" +
				"           <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>\n" +
				"           <CreateTime>1395658984</CreateTime>\n" +
				"           <MsgType><![CDATA[event]]></MsgType>\n" +
				"           <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>\n" +
				"           <MsgID>200163840</MsgID>\n" +
				"           <Status><![CDATA[failed:user block]]></Status>\n" +
				"           </xml>");

		dispatcher.dispatch("<xml>\n" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
				"<FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
				"<CreateTime>123456789</CreateTime>\n" +
				"<MsgType><![CDATA[event]]></MsgType>\n" +
				"<Event><![CDATA[subscribe]]></Event>\n" +
				"</xml>");

		dispatcher.dispatch("<xml><ToUserName><![CDATA[toUser]]></ToUserName>\n" +
				"<FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
				"<CreateTime>123456789</CreateTime>\n" +
				"<MsgType><![CDATA[event]]></MsgType>\n" +
				"<Event><![CDATA[subscribe]]></Event>\n" +
				"<EventKey><![CDATA[qrscene_123123]]></EventKey>\n" +
				"<Ticket><![CDATA[TICKET]]></Ticket>\n" +
				"</xml>");

		dispatcher.dispatch("<xml>\n" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
				"<FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
				"<CreateTime>123456789</CreateTime>\n" +
				"<MsgType><![CDATA[event]]></MsgType>\n" +
				"<Event><![CDATA[SCAN]]></Event>\n" +
				"<EventKey><![CDATA[SCENE_VALUE]]></EventKey>\n" +
				"<Ticket><![CDATA[TICKET]]></Ticket>\n" +
				"</xml>");

		dispatcher.dispatch("<xml>\n" +
				" <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
				" <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
				" <CreateTime>1348831860</CreateTime>\n" +
				" <MsgType><![CDATA[text]]></MsgType>\n" +
				" <Content><![CDATA[this is a test]]></Content>\n" +
				" <MsgId>1234567890123456</MsgId>\n" +
				" </xml>");
	}
}