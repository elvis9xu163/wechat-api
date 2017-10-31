package com.xjd.wechat.callback.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author elvis.xu
 * @since 2017-10-31 10:49
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyVoice extends Reply {
	private String mediaId;

	public ReplyVoice() {
		setMsgType(MsgType.VOICE);
	}

	@Override
	protected String innerXml() {
		return String.format("<Voice>" +
				"<MediaId><![CDATA[%s]]></MediaId>" +
				"</Voice>", trim(mediaId));
	}
}
