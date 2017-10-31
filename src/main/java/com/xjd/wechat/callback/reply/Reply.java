package com.xjd.wechat.callback.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author elvis.xu
 * @since 2017-10-31 10:49
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonPropertyOrder({"msgType", "fromUserName", "toUserName", "createTime"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reply {
	private MsgType msgType;
	private String fromUserName;
	private String toUserName;
	private Long createTime;

	@Override
	public String toString() {
		return toXml();
	}

	public String toXml() {
		String text = "<xml>" +
				"<ToUserName><![CDATA[%s]]></ToUserName>" +
				"<FromUserName><![CDATA[%s]]></FromUserName>" +
				"<CreateTime>%s</CreateTime>" +
				"<MsgType><![CDATA[%s]]></MsgType>" +
				"%s" +
				"</xml>";
		return String.format(text, trim(toUserName), trim(fromUserName), createTime == null ? "" : createTime.toString(), msgType.getCode(), trim(innerXml()));
	}

	protected String innerXml() {
		return "";
	}

	protected static String trim(String s) {
		return s == null ? "" : s.trim();
	}

	public static enum MsgType {
		TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), MUSIC("music"), NEWS("news");

		String code;

		MsgType(String code) {
			this.code = code;
		}

		public static MsgType ofCode(String code) {
			if (code == null) return null;
			for (MsgType value : values()) {
				if (value.getCode().equals(code)) {
					return value;
				}
			}
			return null;
		}

		public String getCode() {
			return code;
		}
	}
}
