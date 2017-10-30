package com.xjd.wechat.api.message.template;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import org.apache.http.HttpResponse;

import com.xjd.wechat.ApiErrCodes;
import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-10-30 16:25
 */
public abstract class TemplateMsgApi {

	public static SendResponse send(String accessToken, String toUserOpenId, String msgTemplateId, String linkUrl, Collection<MsgTemplateParam> params, MiniProgram miniProgram) {
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

		return send(accessToken, ApiUtils.toJson(paramMap));
	}

	public static SendResponse send(String accessToken, String json) {
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send" + "?access_token=" + accessToken;

		HttpResponse response = ApiUtils.postJson(url, json);
		ApiUtils.assertResponseOk(url, response);
		SendResponse entity = ApiUtils.parseJsonResponse(url, response, SendResponse.class);

		if (entity.getErrCode() == ApiErrCodes.SC_OK) {
			entity.setSuccess(true);
		}
		return entity;
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
