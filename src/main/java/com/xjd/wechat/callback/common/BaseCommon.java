package com.xjd.wechat.callback.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xjd.wechat.callback.BaseMsg;

/**
 * @author elvis.xu
 * @since 2017-10-31 09:42
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonPropertyOrder({"msgType", "msgId", "fromUserName", "toUserName", "createTime"})
public class BaseCommon extends BaseMsg {
	private Long msgId;

	public BaseCommon(MsgType msgType) {
		super(msgType);
	}
}
