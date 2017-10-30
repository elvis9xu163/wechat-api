package com.xjd.wechat.web.auth;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xjd.wechat.ApiResponse;

/**
 * @author elvis.xu
 * @since 2017-10-30 14:06
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessToken extends ApiResponse {
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
