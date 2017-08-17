package com.xjd.wechat;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author elvis.xu
 * @since 2017-08-15 16:55
 */
@Getter
@Setter
public class CommonEntity {
	@JsonProperty("errcode")
	private Integer errCode;

	@JsonProperty("errmsg")
	private String errMsg;
}
