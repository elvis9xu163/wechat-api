package com.xjd.wechat.web.auth;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xjd.wechat.ApiCommons;
import com.xjd.wechat.ApiResponse;

/**
 * @author elvis.xu
 * @since 2017-10-30 14:53
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo extends ApiResponse {
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

	@JsonProperty("privilege")
	private String[] privilege;

	// === extend ===
	private ApiCommons.Sex sexEnum;

}
