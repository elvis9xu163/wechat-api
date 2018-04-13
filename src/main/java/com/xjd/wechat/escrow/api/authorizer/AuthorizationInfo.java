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
public class AuthorizationInfo extends ApiResponse {
	@JsonProperty("authorization_info")
	private Info authorizationInfo;

	@Getter
	@Setter
	public static class Info {
		@JsonProperty("authorizer_appid")
		private String authorizerAppId;

		@JsonProperty("authorizer_access_token")
		private String authorizerAccessToken;

		@JsonProperty("expires_in")
		private Integer expiresIn;

		@JsonProperty("authorizer_refresh_token")
		private String authorizerRefreshToken;

		@JsonProperty("func_info")
		private List<FuncInfoItem> funcInfo;
	}

	@Getter
	@Setter
	public static class FuncInfoItem {
		@JsonProperty("funcscope_category")
		private FuncInfoCategory funcScopeCategory;

		@JsonProperty("confirm_info")
		private FuncInfoConfirmInfo confirmInfo;
	}

	@Getter
	@Setter
	public static class FuncInfoCategory {
		@JsonProperty("id")
		private Integer id;
	}

	@Getter
	@Setter
	public static class FuncInfoConfirmInfo {
		@JsonProperty("need_confirm")
		private Integer needConfirm;

		@JsonProperty("already_confirm")
		private Integer alreadyConfirm;

		@JsonProperty("can_confirm")
		private Integer canConfirm;
	}


}
