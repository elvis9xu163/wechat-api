package com.xjd.wechat.escrow.callback.auth;

import java.util.Map;

import com.xjd.wechat.escrow.callback.Dispatcher;

/**
 * @author elvis.xu
 * @since 2018-04-12 15:29
 */
public interface AuthListener extends Dispatcher.Listener {
	default void onAuthorized(Authorized msg, Map context) {
	}
	default void onUnauthorized(Unauthorized msg, Map context) {
	}
	default void onUpdateAuthorized(UpdateAuthorized msg, Map context) {
	}
}
