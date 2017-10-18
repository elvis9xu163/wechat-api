package com.xjd.wechat.message.template;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import org.apache.http.HttpResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xjd.wechat.ApiErrCodes;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.CommonCallback;
import com.xjd.wechat.CommonEntity;

/**
 * 发送模板消息
 * @author elvis.xu
 * @since 2017-10-18 14:36
 */
public class SendTemplateMsgApi {
	private static final String API_SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send";

	public static void sendTemplateMsg(String accessToken, String json, SendTemplateMsgCallback callback) {
		String url = API_SEND_MSG + "?access_token=" + accessToken;

		HttpResponse response = ApiUtils.postJson(url, json);
		ApiUtils.assertResponseOk(url, response);
		SendTemplateMsgEntity entity = ApiUtils.parseJsonResponse(url, response, SendTemplateMsgEntity.class);

		if (entity.getErrCode() == ApiErrCodes.SC_OK) {
			callback.onSuccess(entity.getMsgId());
		} else {
			callback.onFailure(entity.getErrCode(), entity.getErrMsg());
		}
	}

	public static void sendTemplateMsg(String accessToken, String toUserOpenId, String msgTemplateId, String linkUrl, Collection<MsgTemplateParam> params, MiniProgram miniProgram, SendTemplateMsgCallback callback) {
		Map<String, Object> paramMap = new HashMap<>(5);
		paramMap.put("touser", toUserOpenId);
		paramMap.put("template_id", msgTemplateId);
		paramMap.put("url", linkUrl);

		if (miniProgram != null) {
			Map<String, Object> map = new HashMap<>(2);
			map.put("appid", miniProgram.getAppId());
			map.put("pagepath", miniProgram.getPagePath());

			paramMap.put("miniprogram", map);
		}

		if (params != null) {
			Map<String, Object> map = new HashMap<>();

			for (MsgTemplateParam param : params) {
				Map<String, Object> map2 = new HashMap<>(2);
				map2.put("value", param.getValue());
				map2.put("color", param.getColor());

				map.put(param.getKey(), map2);
			}

			paramMap.put("data", map);
		}

		sendTemplateMsg(accessToken, ApiUtils.toJson(paramMap), callback);
	}

	public static interface SendTemplateMsgCallback extends CommonCallback {
		void onSuccess(long msgId);
	}

	@Getter
	@Setter
	public static class SendTemplateMsgEntity extends CommonEntity {
		@JsonProperty("msgid")
		private Long msgId;
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MsgTemplateParam {
		private String key;
		private String value;
		private String color;
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MiniProgram {
		private String appId;
		private String pagePath;
	}
}
