package com.xjd.wechat;

import java.io.IOException;

import com.xjd.wechat.api.message.template.SendResponse;

/**
 * @author elvis.xu
 * @since 2017-10-30 14:33
 */
public class AnyTest {
	public static void main(String[] args) throws IOException {
		String s = "{\"success\":true,\"errcode\":0,\"errmsg\":\"ok\",\"msgid\":9223372036854775807}";
		SendResponse sendResponse = ApiUtils.getObjectMapper().readValue(s, SendResponse.class);
		System.out.println(Long.MAX_VALUE);
		System.out.println(ApiUtils.toJson(sendResponse));
	}
}
