package com.xjd.wechat.callback.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author elvis.xu
 * @since 2017-10-30 17:18
 */
@Getter
@Setter
@Accessors(chain = true)
public class Video extends BaseCommon {
	private String mediaId;
	private String thumbMediaId;

	public Video() {
		super(MsgType.VIDEO);
	}
}
