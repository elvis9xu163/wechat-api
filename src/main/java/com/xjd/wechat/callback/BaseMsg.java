package com.xjd.wechat.callback;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author elvis.xu
 * @since 2017-10-30 17:32
 */

@Getter
@Accessors(chain = true)
@JsonPropertyOrder({"msgType", "fromUserName", "toUserName", "createTime"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseMsg {
	private MsgType msgType;

	@Setter
	private String fromUserName;
	@Setter
	private String toUserName;
	@Setter
	private Long createTime;

	public BaseMsg(MsgType msgType) {
		this.msgType = msgType;
	}

	public static enum MsgType {
		TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), SHORT_VIDEO("shortvideo"), LOCATION("location"), LINK("link"),
		EVENT("event");

		String code;
		MsgType(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public static MsgType ofCode(String code) {
			if (code == null) return null;
			for (MsgType msgType : values()) {
				if (msgType.getCode().equals(code)) {
					return msgType;
				}
			}
			return null;
		}
	}
}
