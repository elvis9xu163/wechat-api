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
public class ReplyMusic extends Reply {
	private String title;
	private String description;
	private String musicUrl;
	private String hQMusicUrl;
	private String thumbMediaId;

	public ReplyMusic() {
		setMsgType(MsgType.MUSIC);
	}

	@Override
	protected String innerXml() {
		return String.format("<Music>" +
				"<Title><![CDATA[%s]]></Title>" +
				"<Description><![CDATA[%s]]></Description>" +
				"<MusicUrl><![CDATA[%s]]></MusicUrl>" +
				"<HQMusicUrl><![CDATA[%s]]></HQMusicUrl>" +
				"<ThumbMediaId><![CDATA[%s]]></ThumbMediaId>" +
				"</Music>", trim(title), trim(description), trim(musicUrl), trim(hQMusicUrl), trim(thumbMediaId));
	}
}
