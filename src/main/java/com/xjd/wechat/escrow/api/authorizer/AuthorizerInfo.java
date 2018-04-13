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
public class AuthorizerInfo extends ApiResponse {
	@JsonProperty("authorizer_info")
	private Authorizer authorizerInfo;

	@JsonProperty("authorization_info")
	private Info authorizationInfo;

	@Getter
	@Setter
	public static class Authorizer {
		@JsonProperty("nick_name")
		private String nickName;

		@JsonProperty("user_name")
		private String userName;

		@JsonProperty("alias")
		private String alias;

		@JsonProperty("head_img")
		private String headImg;

		@JsonProperty("qrcode_url")
		private String qrcodeUrl;

		@JsonProperty("principal_name")
		private String principalName;

		@JsonProperty("signature")
		private String signature;

		@JsonProperty("idc")
		private String idc;

		@JsonProperty("service_type_info")
		private IdObj serviceTypeInfo;

		@JsonProperty("verify_type_info")
		private IdObj verifyTypeInfo;

		@JsonProperty("business_info")
		private BusinessInfo businessInfo;
	}

	@Getter
	@Setter
	public static class IdObj {
		@JsonProperty("id")
		private Integer id;
	}

	@Getter
	@Setter
	public static class BusinessInfo {
		@JsonProperty("open_pay")
		private Integer openPay;

		@JsonProperty("open_shake")
		private Integer openShake;

		@JsonProperty("open_scan")
		private Integer openScan;

		@JsonProperty("open_card")
		private Integer openCard;

		@JsonProperty("open_store")
		private Integer openStore;
	}

	@Getter
	@Setter
	public static class Info {
		@JsonProperty("authorizer_appid")
		private String authorizerAppId;

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
