package com.xjd.wechat.web.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.xjd.wechat.ApiCommons;
import com.xjd.wechat.ApiUtils;

/**
 * web auth
 * @author elvis.xu
 * @since 2017-10-30 13:45
 */
public abstract class WebAuthApi {

	/**
	 * 生成一个授权链接地址
	 * @param appId
	 * @param redirectUri
	 * @param scope
	 * @param state
	 * @return
	 */
	public static String getAuthorizeUrl(String appId, String redirectUri, Scope scope, String state) {
		return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%1s&redirect_uri=%2s&response_type=code&scope=%3s&state=%4s#wechat_redirect",
				appId, redirectUri, scope.getCode(), state);
	}

	public static AccessToken getAccessToken(String appId, String appSecret, String authCode) {
		List<NameValuePair> params = new ArrayList<>(4);
		params.add(new BasicNameValuePair("appid", appId));
		params.add(new BasicNameValuePair("secret", appSecret));
		params.add(new BasicNameValuePair("code", authCode));
		params.add(new BasicNameValuePair("grant_type", "authorization_code"));

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";

		HttpResponse response = ApiUtils.post(url, params);
		ApiUtils.assertResponseOk(url, response);
		AccessToken entity = ApiUtils.parseJsonResponse(url, response, AccessToken.class);

		if (entity.getAccessToken() != null) {
			entity.setSuccess(true);
		}

		return entity;
	}

	public static AccessToken refreshAccessToken(String appId, String refreshToken) {
		List<NameValuePair> params = new ArrayList<>(3);
		params.add(new BasicNameValuePair("appid", appId));
		params.add(new BasicNameValuePair("refresh_token", refreshToken));
		params.add(new BasicNameValuePair("grant_type", "refresh_token"));

		String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

		HttpResponse response = ApiUtils.post(url, params);
		ApiUtils.assertResponseOk(url, response);
		AccessToken entity = ApiUtils.parseJsonResponse(url, response, AccessToken.class);

		if (entity.getAccessToken() != null) {
			entity.setSuccess(true);
		}

		return entity;
	}

	public static UserInfo getUserInfo(String accessToken, String openId, ApiCommons.WLang lang) {
		return getUserInfo(accessToken, openId, lang.getCode());
	}

	public static UserInfo getUserInfo(String accessToken, String openId, String lang) {
		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("lang", lang),
				new BasicNameValuePair("access_token", accessToken),
				new BasicNameValuePair("openid", openId)
		);

		String url = "https://api.weixin.qq.com/sns/userinfo";

		HttpResponse response = ApiUtils.get(url, params);
		ApiUtils.assertResponseOk(url, response);
		UserInfo entity = ApiUtils.parseJsonResponse(url, response, UserInfo.class);

		if (entity.getOpenId() != null) {
			entity.setSuccess(true);
		}
		if (entity.getSex() != null) {
			entity.setSexEnum(ApiCommons.Sex.ofCode(entity.getSex()));
		}
		return entity;
	}

	public static enum Scope {
		BASE("snsapi_base"), USERINFO("snsapi_userinfo");

		String code;

		Scope(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}

}
