package com.xjd.wechat.basic;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.CommonCallback;
import com.xjd.wechat.CommonEntity;

/**
 * 基础支持-获取accessToken API
 *
 * @author elvis.xu
 * @since 2017-08-15 15:04
 */
public abstract class AccessTokenApi {
	public static final String API_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

	public static void getAccessToken(String appId, String appSecret, AccessTokenCallback callback) {
		String url = API_ACCESS_TOKEN;
		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("grant_type", "client_credential"),
				new BasicNameValuePair("appid", appId),
				new BasicNameValuePair("secret", appSecret)
		);

		HttpResponse response = ApiUtils.get(url, params);

		ApiUtils.assertResponseOk(url, response);

		AccessTokenEntity accessTokenEntity = ApiUtils.parseJsonResponse(url, response, AccessTokenEntity.class);

		if (accessTokenEntity.getAccessToken() != null) {
			callback.onSuccess(accessTokenEntity.getAccessToken(), accessTokenEntity.getExpiresIn());
		} else {
			callback.onFailed(accessTokenEntity.getErrCode(), accessTokenEntity.getErrMsg());
		}
	}

	public static interface AccessTokenCallback extends CommonCallback {

		void onSuccess(String accessToken, int expiresInSeconds);

	}

	@Getter
	@Setter
	public static class AccessTokenEntity extends CommonEntity {

		@JsonProperty("access_token")
		private String accessToken;

		@JsonProperty("expires_in")
		private Integer expiresIn;

	}
}
