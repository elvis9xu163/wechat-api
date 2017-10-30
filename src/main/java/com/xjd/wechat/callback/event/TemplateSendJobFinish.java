package com.xjd.wechat.callback.event;

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
public class TemplateSendJobFinish extends BaseEvent {
	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private Long msgID;
	private String status;

	public TemplateSendJobFinish() {
		super(Event.TEMPLATE_SEND_JOB_FINISH);
	}
}
