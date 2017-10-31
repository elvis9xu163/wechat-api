package com.xjd.wechat.callback.reply;

import com.xjd.wechat.ApiException;

/**
 * @author elvis.xu
 * @since 2017-10-31 10:52
 */
public abstract class Replys {

	public static Reply newReply(Reply.MsgType msgType, String fromUserName, String toUserName) {
		return newReply(msgType, fromUserName, toUserName, System.currentTimeMillis() / 1000);
	}

	public static Reply newReply(Reply.MsgType msgType, String fromUserName, String toUserName, Long createTime) {
		if (msgType == null) return null;

		Reply reply = null;
		switch (msgType) {
		case TEXT:
			reply = new ReplyText();
			break;
		case IMAGE:
			reply = new ReplyImage();
			break;
		case VOICE:
			reply = new ReplyVoice();
			break;
		case VIDEO:
			reply = new ReplyVideo();
			break;
		case MUSIC:
			reply = new ReplyMusic();
			break;
		case NEWS:
			reply = new ReplyNews();
			break;
		}
		reply.setFromUserName(fromUserName).setToUserName(toUserName).setCreateTime(createTime);
		return reply;
	}

	public static <T extends Reply> T newReply(Class<T> clazz, String fromUserName, String toUserName) {
		return newReply(clazz, fromUserName, toUserName, System.currentTimeMillis() / 1000);
	}

	public static <T extends Reply> T newReply(Class<T> clazz, String fromUserName, String toUserName, Long createTime) {
		try {
			T t = clazz.getConstructor(null).newInstance(null);
			t.setFromUserName(fromUserName).setToUserName(toUserName).setCreateTime(createTime);
			return t;
		} catch (Exception e) {
			throw new ApiException(e, "cannot create '" + clazz.getName() + "'!");
		}
	}
}
