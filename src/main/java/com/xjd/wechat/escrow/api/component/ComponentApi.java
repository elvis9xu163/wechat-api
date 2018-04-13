package com.xjd.wechat.escrow.api.component;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2018-04-12 16:16
 */
public abstract class ComponentApi {
	public static ComponentAccessToken getComponentAccessToken(String componentAppId, String componentAppSecret, String
			componentVerifyTicket) {
		String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

		Map<String, Object> paramMap = new HashMap<>(3);
		paramMap.put("component_appid", componentAppId);
		paramMap.put("component_appsecret", componentAppSecret);
		paramMap.put("component_verify_ticket", componentVerifyTicket);

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));
		ApiUtils.assertResponseOk(url, response);
		ComponentAccessToken entity = ApiUtils.parseJsonResponse(url, response, ComponentAccessToken.class);

		if (entity.getComponentAccessToken() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}
}
