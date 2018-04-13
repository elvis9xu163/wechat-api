package com.xjd.wechat.escrow.callback.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author elvis.xu
 * @since 2018-04-12 15:20
 */
@Getter
@Setter
@Accessors(chain = true)
public class Unauthorized extends BaseAuth {
	public Unauthorized() {
		super(InfoType.UNAUTHORIZED);
	}
}
