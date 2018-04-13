package com.xjd.wechat.api.jsapi;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2018-04-09 11:24
 */
public class JsApiExample {
	public static void main(String[] args) {
		String appId = "wx3855b6856413c512";
		String ticket = "gdzxN2g622vVfv3lEXnBstnx4c3OzHhbVMRHqSBIzbKbpmibpXhtkXxuT52Y_Vqvlt7iOYacLdhTRIEIsGgwSg";
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		long timestamp = System.currentTimeMillis() / 1000;
		String payUrl = "http://vip3.xxx.vvaccess.com";

		String sign = JsApi.sign(ticket, nonceStr, timestamp, payUrl);

		Map<String, String> map = new HashMap<>();
		map.put("appId", appId);
		map.put("nonceStr", nonceStr);
		map.put("timestamp", timestamp + "");
		map.put("signature", sign);

		System.out.println(ApiUtils.toJson(map));
	}
}
