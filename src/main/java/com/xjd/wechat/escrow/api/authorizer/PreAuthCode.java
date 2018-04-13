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
public class PreAuthCode extends ApiResponse {
	@JsonProperty("pre_auth_code")
	private String preAuthCode;

	@JsonProperty("expires_in")
	private Integer expiresIn;
}
