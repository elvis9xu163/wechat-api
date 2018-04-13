package com.xjd.wechat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author elvis.xu
 * @since 2018-04-09 14:07
 */
public class TestOrder {

	public static void main(String[] args) {

//		order();
//		sign();
		config();
	}

	public static void order() {
		List<NameValuePair> param = new LinkedList<>();
		param.add(new BasicNameValuePair("appid", "wx3855b6856413c512"));
		param.add(new BasicNameValuePair("mch_id", "1375012902"));
		param.add(new BasicNameValuePair("nonce_str", "1234567890"));
		param.add(new BasicNameValuePair("sign_type", "MD5"));
		param.add(new BasicNameValuePair("body", "测试1001"));
		param.add(new BasicNameValuePair("out_trade_no", "TEST1001"));
		param.add(new BasicNameValuePair("total_fee", "1"));
		param.add(new BasicNameValuePair("spbill_create_ip", "121.32.145.0"));
		param.add(new BasicNameValuePair("notify_url", "http://vip2.xxx.vvaccess" +
				".com/api/wechat/escrow/pay/callback"));
		param.add(new BasicNameValuePair("trade_type", "JSAPI"));
		param.add(new BasicNameValuePair("openid", "oWTCBwWqXFntRgkfsGDmKgj7KvjY"));
		String ps = ApiUtils.joinSorted(param) + "&key=" + TestEnv.apiKey;
		String sign = ApiUtils.sign(ps, ApiUtils.ALG_MD5).toUpperCase();
		param.add(new BasicNameValuePair("sign", sign));

		String resStr = httpPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder", param);
	}

	public static void sign() {
		List<NameValuePair> param = new LinkedList<>();
		param.add(new BasicNameValuePair("appId", "wx3855b6856413c512"));
		param.add(new BasicNameValuePair("timeStamp", System.currentTimeMillis() / 1000L + ""));
		param.add(new BasicNameValuePair("nonceStr", "1234567890"));
		param.add(new BasicNameValuePair("package", "prepay_id=wx1118415057700477687b83473477155334"));
		param.add(new BasicNameValuePair("signType", "MD5"));
		String ps = ApiUtils.joinSorted(param) + "&key=" + TestEnv.apiKey;
		System.out.println("原字符串:" + ps);
		String sign = ApiUtils.sign(ps, ApiUtils.ALG_MD5).toUpperCase();
		param.add(new BasicNameValuePair("paySign", sign));

		Map<String, String> map = param.stream().map(e -> {
			if (e.getName().equals("timeStamp")) {
				return new BasicNameValuePair("timestamp", e.getValue()); // 实际使用时要变小写，操蛋的玩意
			} else {
				return e;
			}
		}).collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));

		System.out.println(ApiUtils.toJson(map));

	}

	public static void config() {
		String timestamp = System.currentTimeMillis() / 1000L + "";
		List<NameValuePair> param = new LinkedList<>();
		param.add(new BasicNameValuePair("jsapi_ticket", "gdzxN2g622vVfv3lEXnBstnx4c3OzHhbVMRHqSBIzbJoY8LhQLDmEq-qWrKiqn6MzC4uDWbNaPFwe0o2mknSEw"));
		param.add(new BasicNameValuePair("noncestr", "1234567890"));
		param.add(new BasicNameValuePair("timestamp", timestamp));
		param.add(new BasicNameValuePair("url", "http://vip3.xxx.vvaccess.com"));

		String sign = ApiUtils.sign(param, ApiUtils.ALG_SHA1).toUpperCase();

		Map<String, String> map = new LinkedHashMap<>();
		map.put("appId", "wx3855b6856413c512");
		map.put("nonceStr", "1234567890");
		map.put("timestamp", timestamp);
		map.put("signature", sign);
		System.out.println(ApiUtils.toJson(map));
	}

	protected static String httpPostXml(String url, List<NameValuePair> params) {
		HttpPost hPost = new HttpPost(url);

		String xml = null;
		if (params != null && !params.isEmpty()) {
			xml = params.stream().map(e -> "<" + e.getName() + ">" + e.getValue() + "</" + e.getName() + ">").collect
					(Collectors.joining("", "<xml>", "</xml>"));
			hPost.setEntity(new StringEntity(xml, ContentType.create("application/xml", Charset.forName("utf8"))));
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			long start = System.currentTimeMillis();
			HttpResponse response = httpClient.execute(hPost);

			// 处理响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String resCont = EntityUtils.toString(response.getEntity(), Charset.forName("utf8"));
				System.out.println("res content: " + resCont);
				// 正常返回, 解析返回数据
				return resCont;

			} else {
				throw new RuntimeException("");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			hPost.releaseConnection();
		}
		return null;
	}

	public static String sortToString(Map<String, ?> map) {
		String txt = map.entrySet().stream().sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey())).map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
		return txt;
	}

	public static String digest(String src, String alg) {
		try {
			MessageDigest crypt = MessageDigest.getInstance(alg);
			crypt.reset();
			crypt.update(src.getBytes("UTF-8"));
			return byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	protected static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static String genNonceStr() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
