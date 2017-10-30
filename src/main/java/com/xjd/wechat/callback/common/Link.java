package com.xjd.wechat.callback.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.xjd.wechat.callback.BaseMsg;

/**
 * @author elvis.xu
 * @since 2017-10-30 17:18
 */
@Getter
@Setter
@Accessors(chain = true)
public class Link extends BaseMsg {
	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private Long msgId;
	private String title;
	private String description;
	private String url;

	public Link() {
		super(MsgType.LINK);
	}
}
