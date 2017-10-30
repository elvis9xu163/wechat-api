package com.xjd.wechat.api.basic;

import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-10-30 15:21
 */
public abstract class BasicApi {

	public static AccessToken getAccessToken(String appId, String appSecret) {
		String url = "https://api.weixin.qq.com/cgi-bin/token";

		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("grant_type", "client_credential"),
				new BasicNameValuePair("appid", appId),
				new BasicNameValuePair("secret", appSecret)
		);

		HttpResponse response = ApiUtils.get(url, params);
		ApiUtils.assertResponseOk(url, response);
		AccessToken entity = ApiUtils.parseJsonResponse(url, response, AccessToken.class);

		if (entity.getAccessToken() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}
}
