package com.xjd.wechat.api.account.qrcode;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xjd.wechat.ApiResponse;

/**
 * @author elvis.xu
 * @since 2017-10-30 15:57
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QRCodeTicket extends ApiResponse {
	@JsonProperty("ticket")
	private String ticket;

	@JsonProperty("expire_seconds")
	private Integer expireInseconds;

	@JsonProperty("url")
	private String url;
}
