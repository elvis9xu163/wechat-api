package com.xjd.wechat.escrow.callback.component;

import java.util.Map;

import com.xjd.wechat.escrow.callback.Dispatcher;

/**
 * @author elvis.xu
 * @since 2018-04-12 14:35
 */
public interface ComponentListener extends Dispatcher.Listener {
	default void onComponentVerifyTicket(ComponentVerifyTicket msg, Map context) {
	}
}
