package com.xjd.wechat.escrow.callback.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xjd.wechat.escrow.callback.BaseMsg;


/**
 * @author elvis.xu
 * @since 2018-04-12 15:14
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonPropertyOrder({"appId", "infoType", "createTime"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseAuth extends BaseMsg {
	private String authorizerAppId;

	public BaseAuth(InfoType infoType) {
		super(infoType);
	}
}
