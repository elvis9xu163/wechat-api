package com.xjd.wechat.callback.event;

import java.util.Map;

/**
 * 事件回调消息处理器
 * @author elvis.xu
 * @since 2017-10-30 18:49
 */
public interface EventListener {
	default void onSubscribe(Subscribe msg, Map context) {
	}
	default void onUnsubscribe(Unsubscribe msg, Map context) {
	}
	default void onScan(Scan msg, Map context) {
	}
	default void onLocation(LocationEvent msg, Map context) {
	}
	default void onClick(Click msg, Map context) {
	}
	default void onTemplateSendJobFinish(TemplateSendJobFinish msg, Map context) {
	}
}
