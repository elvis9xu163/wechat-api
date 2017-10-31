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
public class ReplyImage extends Reply {
	private String mediaId;

	public ReplyImage() {
		setMsgType(MsgType.IMAGE);
	}

	@Override
	protected String innerXml() {
		return String.format("<Image>" +
				"<MediaId><![CDATA[%s]]></MediaId>" +
				"</Image>", trim(mediaId));
	}
}
