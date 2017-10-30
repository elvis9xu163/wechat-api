package com.xjd.wechat.callback.event;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xjd.wechat.callback.BaseMsg;

/**
 * @author elvis.xu
 * @since 2017-10-30 18:52
 */
@Getter
@JsonPropertyOrder({"msgType", "event"})
public class BaseEvent extends BaseMsg {
	private Event event;

	public BaseEvent(Event event) {
		super(MsgType.EVENT);
		this.event = event;
	}

	public static enum Event {
		SUBSCRIBE("subscribe"),
		UNSUBSCRIBE("unsubscribe"),
		SCAN("SCAN"),
		LOCATION("LOCATION"),
		CLICK("CLICK"),
		TEMPLATE_SEND_JOB_FINISH("TEMPLATESENDJOBFINISH")
		;

		String code;
		Event(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public static Event ofCode(String code) {
			if (code == null) return null;
			for (Event value : values()) {
				if (value.getCode().equals(code)) {
					return value;
				}
			}
			return null;
		}
	}
}
