package com.xjd.wechat.escrow.callback;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author elvis.xu
 * @since 2018-04-12 14:38
 */
@Getter
@Accessors(chain = true)
@JsonPropertyOrder({"appId", "infoType", "createTime"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseMsg {
	private InfoType infoType;
	@Setter
	private String appId;
	@Setter
	private Long createTime;

	public BaseMsg(InfoType infoType) {
		this.infoType = infoType;
	}

	public static enum InfoType {
		COMPONENT_VERIFY_TICKET("component_verify_ticket"), AUTHORIZED("authorized"), UPDATE_AUTHORIZED
				("updateauthorized"), UNAUTHORIZED("unauthorized");

		String code;
		InfoType(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public static InfoType ofCode(String code) {
			if (code == null) return null;
			for (InfoType msgType : values()) {
				if (msgType.getCode().equals(code)) {
					return msgType;
				}
			}
			return null;
		}
	}
}
