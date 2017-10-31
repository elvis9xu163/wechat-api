package com.xjd.wechat.callback.common;

import java.math.BigDecimal;

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
public class Location extends BaseCommon {
	private BigDecimal locationX;
	private BigDecimal locationY;
	private BigDecimal scale;
	private String label;

	public Location() {
		super(MsgType.LOCATION);
	}
}
