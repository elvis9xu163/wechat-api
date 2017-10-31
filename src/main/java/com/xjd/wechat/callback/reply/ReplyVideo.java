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
public class ReplyVideo extends Reply {
	private String mediaId;
	private String title;
	private String description;

	public ReplyVideo() {
		setMsgType(MsgType.VIDEO);
	}

	@Override
	protected String innerXml() {
		return String.format("<Video>" +
				"<MediaId><![CDATA[%s]]></MediaId>" +
				"<Title><![CDATA[%s]]></Title>" +
				"<Description><![CDATA[%s]]></Description>" +
				"</Video>", trim(mediaId), trim(title), trim(description));
	}
}
