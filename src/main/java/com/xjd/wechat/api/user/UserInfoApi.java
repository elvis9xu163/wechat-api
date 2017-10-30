package com.xjd.wechat.api.user;

import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.xjd.wechat.ApiCommons;
import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-10-30 15:27
 */
public abstract class UserInfoApi {
	public static UserInfo getUserInfo(String accessToken, String openId, ApiCommons.WLang lang) {
		return getUserInfo(accessToken, openId, lang.getCode());
	}

	public static UserInfo getUserInfo(String accessToken, String openId, String lang) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info";

		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("lang", lang),
				new BasicNameValuePair("access_token", accessToken),
				new BasicNameValuePair("openid", openId)
		);

		HttpResponse response = ApiUtils.get(url, params);
		ApiUtils.assertResponseOk(url, response);
		UserInfo entity = ApiUtils.parseJsonResponse(url, response, UserInfo.class);

		if (entity.getOpenId() != null) {
			entity.setSuccess(true);
		}
		if (entity.getSex() != null) {
			entity.setSexEnum(ApiCommons.Sex.ofCode(entity.getSex()));
		}
		if (entity.getSubscribe() != null) {
			entity.setSubscribeFlag(entity.getSubscribe() == 1 ? true : false);
		}
		return entity;
	}
}
