package com.xjd.wechat;

import java.io.IOException;

/**
 * @author elvis.xu
 * @since 2017-10-30 14:33
 */
public class AnyTest {
	public static void main(String[] args) throws IOException {
//		String s = "{\"success\":true,\"errcode\":0,\"errmsg\":\"ok\",\"msgid\":9223372036854775807}";
//		SendResponse sendResponse = ApiUtils.getObjectMapper().readValue(s, SendResponse.class);
//		System.out.println(Long.MAX_VALUE);
//		System.out.println(ApiUtils.toJson(sendResponse));

		String s = "<xml>" +
				"<ToUserName><![CDATA[%s]]></ToUserName>" +
				"<FromUserName><![CDATA[%s]]></FromUserName>" +
				"<CreateTime>%d</CreateTime>" +
				"<MsgType><![CDATA[%s]]></MsgType>" +
				"<Content><![CDATA[%s]]></Content>" +
				"</xml>";
		System.out.println(String.format(s, "toUser", "fromUser", 100L, "text", ""));

		System.out.println(String.format("%s, %s, %d", "ha", "你好", 100));
	}
}
