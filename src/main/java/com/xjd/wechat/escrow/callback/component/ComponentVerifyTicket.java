package com.xjd.wechat.escrow.callback.component;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.xjd.wechat.escrow.callback.BaseMsg;

/**
 * @author elvis.xu
 * @since 2018-04-12 15:20
 */
@Getter
@Setter
@Accessors(chain = true)
public class ComponentVerifyTicket extends BaseMsg {
	private String componentVerifyTicket;

	public ComponentVerifyTicket() {
		super(InfoType.COMPONENT_VERIFY_TICKET);
	}
}
