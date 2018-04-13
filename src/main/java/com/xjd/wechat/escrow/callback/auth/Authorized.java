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
public class Authorized extends BaseAuth {
	private String authorizationCode;
	private Long authorizationCodeExpiredTime;
	private String preAuthCode;

	public Authorized() {
		super(InfoType.AUTHORIZED);
	}
}
