package com.xjd.wechat.callback.event;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author elvis.xu
 * @since 2017-10-30 17:18
 */
@Getter
@Setter
@Accessors(chain = true)
public class Unsubscribe extends BaseEvent {

	public Unsubscribe() {
		super(Event.UNSUBSCRIBE);
	}
}
