package com.xjd.wechat.callback.reply;

import java.util.ArrayList;
import java.util.Collection;

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
public class ReplyNews extends Reply {
	private Collection<Item> items = new ArrayList<>();

	public ReplyNews() {
		setMsgType(MsgType.NEWS);
	}


	public ReplyNews addItem(Item... itemArray) {
		if (itemArray == null) return this;
		for (Item item : itemArray) {
			items.add(item);
		}
		return this;
	}

	public ReplyNews addItem(String title, String description, String picUrl, String url) {
		items.add(new Item().setTitle(title).setDescription(description).setPicUrl(picUrl).setUrl(url));
		return this;
	}

	@Override
	protected String innerXml() {
		String itemStr = "";
		if (items != null) {
			for (Item item : items) {
				itemStr += item.innerXml();
			}
		}
		return String.format("<ArticleCount>%d</ArticleCount><Articles>%s</Articles>", items == null ? 0 : items.size(), itemStr);
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Item {
		private String title;
		private String description;
		private String picUrl;
		private String url;

		protected String innerXml() {
			return String.format("<item>" +
					"<Title><![CDATA[%s]]></Title> " +
					"<Description><![CDATA[%s]]></Description>" +
					"<PicUrl><![CDATA[%s]]></PicUrl>" +
					"<Url><![CDATA[%s]]></Url>" +
					"</item>", ReplyNews.trim(title), ReplyNews.trim(description), ReplyNews.trim(picUrl), ReplyNews.trim(url));
		}
	}
}
