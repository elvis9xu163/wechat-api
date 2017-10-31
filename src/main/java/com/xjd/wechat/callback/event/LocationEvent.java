package com.xjd.wechat.callback.event;

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
public class LocationEvent extends BaseEvent {
	private BigDecimal latitude;
	private BigDecimal longitude;
	private BigDecimal precision;

	public LocationEvent() {
		super(Event.LOCATION);
	}
}
