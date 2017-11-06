package com.xjd.wechat.api.jsapi;

import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-11-06 11:51
 */
public abstract class JsApi {

	public static Ticket getTicket(String accessToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("access_token", accessToken),
				new BasicNameValuePair("type", "jsapi")
		);

		HttpResponse response = ApiUtils.get(url, params);
		ApiUtils.assertResponseOk(url, response);
		Ticket entity = ApiUtils.parseJsonResponse(url, response, Ticket.class);

		if (entity.getTicket() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static String sign(String ticket, String nonceStr, long timestampInSeconds, String url) {
		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("jsapi_ticket", ticket),
				new BasicNameValuePair("noncestr", nonceStr),
				new BasicNameValuePair("timestamp", timestampInSeconds + ""),
				new BasicNameValuePair("url", url)
		);
		return ApiUtils.sign(params, ApiUtils.ALG_SHA1);
	}
}
