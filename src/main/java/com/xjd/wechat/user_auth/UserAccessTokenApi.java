package com.xjd.wechat.user_auth;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xjd.wechat.ApiErrCodes;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.CommonCallback;
import com.xjd.wechat.CommonEntity;

/**
 * 用户授权API
 * @author elvis.xu
 * @since 2017-08-15 18:41
 */
public abstract class UserAccessTokenApi {
	public static final String API_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public static final String API_REFRESH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	public static final String API_CHECK_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/auth";

	/**
	 * 使用授权码换取accessToken
	 * @param appId
	 * @param appSecret
	 * @param authCode
	 * @param callback
	 */
	public static void getAccessToken(String appId, String appSecret, String authCode, UserAccessTokenCallback callback) {
		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("grant_type", "authorization_code"),
				new BasicNameValuePair("code", authCode),
				new BasicNameValuePair("appid", appId),
				new BasicNameValuePair("secret", appSecret)
		);

		HttpResponse response = ApiUtils.get(API_ACCESS_TOKEN, params);

		ApiUtils.assertResponseOk(API_ACCESS_TOKEN, response);

		UserAccessTokenEntity entity = ApiUtils.parseJsonResponse(API_ACCESS_TOKEN, response, UserAccessTokenEntity.class);

		if (entity.getAccessToken() != null) {
			callback.onSuccess(entity.getAccessToken(), entity.getExpiresIn(), entity.getRefreshToken(), entity.getOpenId(), entity.getUnionId(), entity.getScope());
		} else {
			callback.onFailed(entity.getErrCode(), entity.getErrMsg());
		}
	}

	/**
	 * 刷新accessToken
	 * @param appId
	 * @param refreshToken
	 * @param callback
	 */
	public static void refreshAccessToken(String appId, String refreshToken, UserAccessTokenCallback callback) {
		String url = API_REFRESH_ACCESS_TOKEN;

		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("grant_type", "refresh_token"),
				new BasicNameValuePair("appid", appId),
				new BasicNameValuePair("refresh_token", refreshToken)
		);

		HttpResponse response = ApiUtils.get(url, params);

		ApiUtils.assertResponseOk(url, response);

		UserAccessTokenEntity entity = ApiUtils.parseJsonResponse(url, response, UserAccessTokenEntity.class);

		if (entity.getAccessToken() != null) {
			callback.onSuccess(entity.getAccessToken(), entity.getExpiresIn(), entity.getRefreshToken(), entity.getOpenId(), entity.getUnionId(), entity.getScope());
		} else {
			callback.onFailed(entity.getErrCode(), entity.getErrMsg());
		}
	}

	/**
	 * 校验accessToken是否有效
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static boolean checkAccessToken(String accessToken, String openId) {
		String url = API_CHECK_ACCESS_TOKEN;
		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("access_token", accessToken),
				new BasicNameValuePair("openid", openId)
		);

		HttpResponse response = ApiUtils.get(url, params);

		ApiUtils.assertResponseOk(url, response);

		CommonEntity entity = ApiUtils.parseJsonResponse(url, response, CommonEntity.class);

		if (entity.getErrCode() == ApiErrCodes.SC_OK) {
			return true;
		} else {
			return false;
		}
	}

	public static interface UserAccessTokenCallback extends CommonCallback {
		void onSuccess(String accessToken, int expiresInSeconds, String refreshToken, String openId, String unionId, String scope);
	}

	@Getter
	@Setter
	public static class UserAccessTokenEntity extends CommonEntity {

		@JsonProperty("access_token")
		private String accessToken;

		@JsonProperty("expires_in")
		private Integer expiresIn;

		@JsonProperty("refresh_token")
		private String refreshToken;

		@JsonProperty("openid")
		private String openId;

		@JsonProperty("scope")
		private String scope;

		@JsonProperty("unionid")
		private String unionId;

	}
}
