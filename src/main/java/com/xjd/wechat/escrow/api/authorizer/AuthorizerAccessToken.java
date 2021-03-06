package com.xjd.wechat.escrow.api.authorizer;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xjd.wechat.ApiResponse;

/**
 * @author elvis.xu
 * @since 2017-10-30 15:22
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizerAccessToken extends ApiResponse {
	@JsonProperty("authorizer_access_token")
	private String authorizerAccessToken;

	@JsonProperty("expires_in")
	private Integer expiresIn;

	@JsonProperty("authorizer_refresh_token")
	private String authorizerRefreshToken;
}
