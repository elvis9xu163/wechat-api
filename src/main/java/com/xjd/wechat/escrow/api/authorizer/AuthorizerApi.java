package com.xjd.wechat.escrow.api.authorizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.xjd.wechat.ApiResponse;
import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2018-04-12 16:20
 */
public abstract class AuthorizerApi {
	public static PreAuthCode getPreAuthCode(String componentAppId, String componentAccessToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode";

		url += "?component_access_token=" + componentAccessToken;

		Map<String, Object> paramMap = new HashMap<>(1);
		paramMap.put("component_appid", componentAppId);

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));
		ApiUtils.assertResponseOk(url, response);
		PreAuthCode entity = ApiUtils.parseJsonResponse(url, response, PreAuthCode.class);

		if (entity.getPreAuthCode() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static String getAuthUrl(String componentAppId, String preAuthCode, String redirectUri, Integer authType) {
		String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage";

		List<NameValuePair> params = new ArrayList<>(4);
		params.add(new BasicNameValuePair("component_appid", componentAppId));
		params.add(new BasicNameValuePair("pre_auth_code", preAuthCode));
		params.add(new BasicNameValuePair("redirect_uri", redirectUri));
		if (authType != null) {
			params.add(new BasicNameValuePair("auth_type", authType.toString()));
		}

		return url + "?" + URLEncodedUtils.format(params, ApiUtils.getDefaultCharset());
	}

	public static AuthorizationInfo authorize(String componentAppId, String componentAccessToken, String
			authorizationCode) {
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth";

		url += "?component_access_token=" + componentAccessToken;

		Map<String, Object> paramMap = new HashMap<>(2);
		paramMap.put("component_appid", componentAppId);
		paramMap.put("authorization_code", authorizationCode);

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));
		ApiUtils.assertResponseOk(url, response);
		AuthorizationInfo entity = ApiUtils.parseJsonResponse(url, response, AuthorizationInfo.class);

		if (entity.getAuthorizationInfo() != null && entity.getAuthorizationInfo().getAuthorizerAppId() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static AuthorizerAccessToken refreshAuthorizerAccessToken(String componentAppId, String componentAccessToken,
			String authorizerAppId, String authorizerRefreshToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token";

		url += "?component_access_token=" + componentAccessToken;

		Map<String, Object> paramMap = new HashMap<>(3);
		paramMap.put("component_appid", componentAppId);
		paramMap.put("authorizer_appid", authorizerAppId);
		paramMap.put("authorizer_refresh_token", authorizerRefreshToken);

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));
		ApiUtils.assertResponseOk(url, response);
		AuthorizerAccessToken entity = ApiUtils.parseJsonResponse(url, response, AuthorizerAccessToken.class);

		if (entity.getAuthorizerAccessToken() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static AuthorizerInfo getAuthorizerInfo(String componentAppId, String componentAccessToken,
												   String authorizerAppId) {
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info";

		url += "?component_access_token=" + componentAccessToken;

		Map<String, Object> paramMap = new HashMap<>(2);
		paramMap.put("component_appid", componentAppId);
		paramMap.put("authorizer_appid", authorizerAppId);

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));
		ApiUtils.assertResponseOk(url, response);
		AuthorizerInfo entity = ApiUtils.parseJsonResponse(url, response, AuthorizerInfo.class);

		if (entity.getAuthorizerInfo() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static AuthorizerList getAuthorizerList(String componentAppId, String componentAccessToken, Long offset,
												   Integer limit) {
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_list";

		url += "?component_access_token=" + componentAccessToken;

		Map<String, Object> paramMap = new HashMap<>(3);
		paramMap.put("component_appid", componentAppId);
		paramMap.put("offset", offset);
		paramMap.put("count", limit);

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));
		ApiUtils.assertResponseOk(url, response);
		AuthorizerList entity = ApiUtils.parseJsonResponse(url, response, AuthorizerList.class);

		if (entity.getTotalCount() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static AuthorizerOption getAuthorizerOption(String componentAppId, String componentAccessToken, String
			authorizerAppId, String optionName) {
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_option";

		url += "?component_access_token=" + componentAccessToken;

		Map<String, Object> paramMap = new HashMap<>(3);
		paramMap.put("component_appid", componentAppId);
		paramMap.put("authorizer_appid", authorizerAppId);
		paramMap.put("option_name", optionName);

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));
		ApiUtils.assertResponseOk(url, response);
		AuthorizerOption entity = ApiUtils.parseJsonResponse(url, response, AuthorizerOption.class);

		if (entity.getAuthorizerAppId() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static ApiResponse setAuthorizerOption(String componentAppId, String componentAccessToken, String
			authorizerAppId, String optionName, String optionValue) {
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_set_authorizer_option";

		url += "?component_access_token=" + componentAccessToken;

		Map<String, Object> paramMap = new HashMap<>(3);
		paramMap.put("component_appid", componentAppId);
		paramMap.put("authorizer_appid", authorizerAppId);
		paramMap.put("option_name", optionName);
		paramMap.put("option_value", optionValue);

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));
		ApiUtils.assertResponseOk(url, response);
		ApiResponse entity = ApiUtils.parseJsonResponse(url, response, ApiResponse.class);

		if (entity.getErrCode() == 0) {
			entity.setSuccess(true);
		}
		return entity;
	}
}
