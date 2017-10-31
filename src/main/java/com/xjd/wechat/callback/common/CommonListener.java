package com.xjd.wechat.callback.common;

import java.util.Map;

import com.xjd.wechat.callback.Dispatcher;

/**
 * 普通回调消息处理器
 * @author elvis.xu
 * @since 2017-10-30 17:12
 */
public interface CommonListener extends Dispatcher.Listener {
	default void onText(Text msg, Map context) {
	}

	default void onImage(Image msg, Map context) {
	}

	default void onVoice(Voice msg, Map context) {
	}

	default void onVideo(Video msg, Map context) {
	}

	default void onShortVideo(ShortVideo msg, Map context) {
	}

	default void onLocation(Location msg, Map context) {
	}

	default void onLink(Link msg, Map context) {
	}

}
