package com.xjd.wechat.customer_service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.xjd.wechat.ApiErrCodes;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.CommonCallback;
import com.xjd.wechat.CommonEntity;

/**
 * 客服-发送客服消息API
 * @author elvis.xu
 * @since 2017-08-15 17:44
 */
public abstract class SendCustomerMsgApi {
	private static final String API_SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

	public static void sendCustomerMsg(String accessToken, String json, SendCustomerMsgCallback callback) {
		String url = API_SEND_MSG + "?access_token=" + accessToken;

		HttpResponse response = ApiUtils.postJson(url, json);
		ApiUtils.assertResponseOk(url, response);
		CommonEntity entity = ApiUtils.parseJsonResponse(url, response, CommonEntity.class);

		if (entity.getErrCode() == ApiErrCodes.SC_OK) {
			callback.onSuccess();
		} else {
			callback.onFailure(entity.getErrCode(), entity.getErrMsg());
		}
	}

	public static void sendCustomerMsgText(String accessToken, String toUserOpenId, String content, SendCustomerMsgCallback callback) {
		Map<String, Object> paramMap = new HashMap<>(3);

		Map<String, Object> textMap = new HashMap<>(1);
		textMap.put("content", content);

		paramMap.put("touser", toUserOpenId);
		paramMap.put("msgtype", "text");
		paramMap.put("text", textMap);

		sendCustomerMsg(accessToken, ApiUtils.toJson(paramMap), callback);
	}

	public static interface SendCustomerMsgCallback extends CommonCallback {
		void onSuccess();
	}

}
