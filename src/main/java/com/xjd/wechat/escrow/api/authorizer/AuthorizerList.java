package com.xjd.wechat.escrow.api.authorizer;

import java.util.List;

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
public class AuthorizerList extends ApiResponse {
	@JsonProperty("total_count")
	private Long totalCount;

	@JsonProperty("list")
	private List<Item> list;

	@Getter
	@Setter
	public static class Item {
		@JsonProperty("authorizer_appid")
		private String authorizerAppid;

		@JsonProperty("refresh_token")
		private String refreshToken;

		@JsonProperty("auth_time")
		private Long authTime;
	}

}
