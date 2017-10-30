package com.xjd.wechat.api.account.qrcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-10-30 15:53
 */
public abstract class QRCodeApi {
	public static QRCodeTicket getQRCodeTicket(String accessToken, boolean temporary, Integer expireInSeconds, Integer sceneId, String sceneStr) {
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create" + "?access_token=" + accessToken;

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
		QRCodeTicket entity = ApiUtils.parseJsonResponse(url, response, QRCodeTicket.class);

		if (entity.getTicket() != null) {
			entity.setSuccess(true);
		}
		return entity;
	}

	public static byte[] getQRCodeImage(String ticket) {
		String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode";

		List<NameValuePair> params = Arrays.asList(
				new BasicNameValuePair("ticket", ticket)
		);

		HttpResponse response = ApiUtils.get(url, params);
		ApiUtils.assertResponseOk(url, response);
		return ApiUtils.parseBytesResponse(url, response);
	}
}
