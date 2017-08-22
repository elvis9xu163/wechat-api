package com.xjd.wechat.user;

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
 * 用户管理-获取用户基本信息API
 * @author elvis.xu
 * @since 2017-08-15 15:04
 */
public abstract class UserInfoApi {
	public static final String API_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";

	/**
	 * @param accessToken
	 * @param openId
	 * @param lang zh_CN | zh_TW | en
	 * @param callback
	 */
	public static void getUserInfo(String accessToken, String openId, String lang, UserInfoCallback callback) {
		String url = API_USER_INFO;
		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("lang", lang),
				new BasicNameValuePair("access_token", accessToken),
				new BasicNameValuePair("openid", openId)
		);

		HttpResponse response = ApiUtils.get(url, params);

		ApiUtils.assertResponseOk(url, response);

		UserInfoEntity entity = ApiUtils.parseJsonResponse(url, response, UserInfoEntity.class);

		if (entity.getSubscribe() != null) {
			callback.onSuccess(entity);
		} else {
			callback.onFailure(entity.getErrCode(), entity.getErrMsg());
		}
	}

	public static interface UserInfoCallback extends CommonCallback {
		void onSuccess(UserInfoEntity entity);
	}

	@Getter
	@Setter
	public static class UserInfoEntity extends CommonEntity {

		/**
		 * 0-未关注, 1-已关注
		 */
		@JsonProperty("subscribe")
		private Integer subscribe;

		@JsonProperty("subscribe_time")
		private Long subscribeTimeInSeconds;

		@JsonProperty("remark")
		private String remark;

		@JsonProperty("groupId")
		private Long groupId;

		@JsonProperty("tagid_list")
		private Long[] tagIds;

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
		private Integer sex;

		@JsonProperty("country")
		private String country;

		@JsonProperty("province")
		private String province;

		@JsonProperty("city")
		private String city;

		@JsonProperty("language")
		private String language;
	}
}
