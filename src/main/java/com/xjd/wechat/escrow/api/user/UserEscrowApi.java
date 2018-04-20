package com.xjd.wechat.escrow.api.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.xjd.wechat.ApiCommons;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.web.auth.UserInfo;
import com.xjd.wechat.web.auth.WebAuthApi;

/**
 * @author elvis.xu
 * @since 2018-04-12 16:19
 */
public abstract class UserEscrowApi {
	public static String getAuthUrl(String componentAppId, String appId, String redirectUri, String scope, String
			state) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize";

		List<NameValuePair> params = new ArrayList<>(5);
		params.add(new BasicNameValuePair("component_appid", componentAppId));
		params.add(new BasicNameValuePair("appid", appId));
		params.add(new BasicNameValuePair("redirect_uri", redirectUri));
		params.add(new BasicNameValuePair("response_type", "code"));
		params.add(new BasicNameValuePair("scope", scope));
		if (state != null) {
			params.add(new BasicNameValuePair("state", state));
		}

		return url + "?" + URLEncodedUtils.format(params, ApiUtils.getDefaultCharset());
	}

	public static AccessToken authorize(String componentAppId, String componentAccessToken, String
			appId, String authorizationCode) {
		String url = "https://api.weixin.qq.com/sns/oauth2/component/access_token";

		List<NameValuePair> params = new ArrayList<>(5);
		params.add(new BasicNameValuePair("component_appid", componentAppId));
		params.add(new BasicNameValuePair("component_access_token", componentAccessToken));
		params.add(new BasicNameValuePair("appid", appId));
		params.add(new BasicNameValuePair("code", authorizationCode));
		params.add(new BasicNameValuePair("grant_type", "authorization_code"));

		HttpResponse response = ApiUtils.get(url, params);
		ApiUtils.assertResponseOk(url, response);
		AccessToken entity = ApiUtils.parseJsonResponse(url, response, AccessToken.class);

		if (entity.getAccessToken() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static AccessToken refreshAccessToken(String componentAppId, String componentAccessToken, String
			appId, String refreshToken) {
		String url = "https://api.weixin.qq.com/sns/oauth2/component/refresh_token";

		List<NameValuePair> params = new ArrayList<>(5);
		params.add(new BasicNameValuePair("component_appid", componentAppId));
		params.add(new BasicNameValuePair("component_access_token", componentAccessToken));
		params.add(new BasicNameValuePair("appid", appId));
		params.add(new BasicNameValuePair("refresh_token", refreshToken));
		params.add(new BasicNameValuePair("grant_type", "refresh_token"));

		HttpResponse response = ApiUtils.get(url, params);
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
		return WebAuthApi.getUserInfo(accessToken, openId, lang);
	}

}
