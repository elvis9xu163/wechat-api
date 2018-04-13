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
public class AuthorizerOption extends ApiResponse {
	@JsonProperty("authorizer_appid")
	private String authorizerAppId;

	@JsonProperty("option_name")
	private String optionName;

	@JsonProperty("option_value")
	private String optionValue;

}
