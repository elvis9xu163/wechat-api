package com.xjd.wechat;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author elvis.xu
 * @since 2017-08-15 16:55
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"success", "errcode", "errmsg"})
public class ApiResponse {
	@JsonProperty("errcode")
	private Integer errCode;

	@JsonProperty("errmsg")
	private String errMsg;

	// === extend ===
	private boolean success = false;
}
