package com.xjd.wechat.user_auth;

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
 * 用户信息获取API
 * @author elvis.xu
 * @since 2017-08-15 18:41
 */
public abstract class UserUserInfoApi {
	public static final String API_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";

	/**
	 * 获取用户基本信息
	 * @param accessToken
	 * @param openId
	 * @param lang  zh_CN | zh_TW | en
	 * @param callback
	 */
	public static void getUserInfo(String accessToken, String openId, String lang, UserUserInfoCallback callback) {
		String url = API_USER_INFO;

		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("lang", lang),
				new BasicNameValuePair("access_token", accessToken),
				new BasicNameValuePair("openid", openId)
		);

		HttpResponse response = ApiUtils.get(url, params);

		ApiUtils.assertResponseOk(url, response);

		UserUserInfoEntity entity = ApiUtils.parseJsonResponse(url, response, UserUserInfoEntity.class);

		if (entity.getOpenId() != null) {
			callback.onSuccess(entity);
		} else {
			callback.onFailure(entity.getErrCode(), entity.getErrMsg());
		}
	}

	public static interface UserUserInfoCallback extends CommonCallback {
		void onSuccess(UserUserInfoEntity entity);
	}

	@Getter
	@Setter
	public static class UserUserInfoEntity extends CommonEntity {

		@JsonProperty("openid")
		private String openId;

		@JsonProperty("unionid")
		private String unionId;

		@JsonProperty("nickname")
		private String nickname;

		@JsonProperty("headimgurl")
		private String headImgUrl;

		/**
		 * 0-未知, 1-男, 2-女
		 */
		@JsonProperty("sex")
		private String sex;

		@JsonProperty("country")
		private String country;

		@JsonProperty("province")
		private String province;

		@JsonProperty("city")
		private String city;

		@JsonProperty("language")
		private String language;

		@JsonProperty("privilege")
		private String[] privilege;

	}

}
