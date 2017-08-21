package com.xjd.wechat.promotion;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.apache.http.HttpResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.CommonCallback;
import com.xjd.wechat.CommonEntity;

/**
 * 获取带参二维码的ticket API
 * @author elvis.xu
 * @since 2017-08-15 16:34
 */
public abstract class QRCodeTicketApi {
	public static final String API_QRCODE_TICKET= "https://api.weixin.qq.com/cgi-bin/qrcode/create";

	public static void getQRCodeTicket(String accessToken, boolean temporary, Integer expireInSeconds, Integer sceneId, String sceneStr, QRCodeTicketCallback callback) {
		String url = API_QRCODE_TICKET + "?access_token=" + accessToken;

		Map<String, Object> paramMap = new HashMap<>(3);

		Map<String, Object> infoMap = new HashMap<>(1);
		Map<String, Object> sceneMap = new HashMap<>(1);
		paramMap.put("action_info", infoMap);
		infoMap.put("scene", sceneMap);

		if (temporary) {
			if (expireInSeconds != null) {
				paramMap.put("expire_seconds", expireInSeconds);
			}
			if (sceneId != null) {
				paramMap.put("action_name", "QR_SCENE");
				sceneMap.put("scene_id", sceneId);
			} else {
				paramMap.put("action_name", "QR_STR_SCENE");
				sceneMap.put("scene_str", sceneStr);
			}
		} else {
			if (sceneId != null) {
				paramMap.put("action_name", "QR_LIMIT_SCENE");
				sceneMap.put("scene_id", sceneId);
			} else {
				paramMap.put("action_name", "QR_LIMIT_STR_SCENE");
				sceneMap.put("scene_str", sceneStr);
			}
		}

		HttpResponse response = ApiUtils.postJson(url, ApiUtils.toJson(paramMap));

		ApiUtils.assertResponseOk(url, response);

		QRCodeTicketEntity entity = ApiUtils.parseJsonResponse(url, response, QRCodeTicketEntity.class);

		if (entity.getTicket() != null) {
			callback.onSuccess(entity.getTicket(), entity.getExpireInseconds(), entity.getUrl());
		} else {
			callback.onFailure(entity.getErrCode(), entity.getErrMsg());
		}
	}

	public static interface QRCodeTicketCallback extends CommonCallback {
		void onSuccess(String ticket, int expireInSeconds, String url);
	}

	@Getter
	@Setter
	public static class QRCodeTicketEntity extends CommonEntity {

		@JsonProperty("ticket")
		private String ticket;

		@JsonProperty("expire_seconds")
		private Integer expireInseconds;

		@JsonProperty("url")
		private String url;
	}
}
