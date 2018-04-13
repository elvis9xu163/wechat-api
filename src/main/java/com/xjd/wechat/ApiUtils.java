package com.xjd.wechat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author elvis.xu
 * @since 2017-08-15 15:13
 */
public abstract class ApiUtils {
	public static final String ALG_SHA1 = "SHA-1";
	public static final String ALG_MD5 = "MD5";

	protected static ObjectMapper objectMapper = new ObjectMapper();
	protected static CloseableHttpClient httpClient = HttpClients.createDefault();
	protected static Charset DEFAULT_CHARSET = Charset.forName("utf8");

	public static void setDefaultCharset(Charset charset) {
		if (charset == null) {
			throw new IllegalArgumentException("default charset cannot set to null.");
		}
		DEFAULT_CHARSET = charset;
	}

	public static Charset getDefaultCharset() {
		return DEFAULT_CHARSET;
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static HttpResponse get(String url, List<NameValuePair> params) {
		String urlCopy = url;
		if (params != null && !params.isEmpty()) {
			urlCopy = urlCopy + "?" + URLEncodedUtils.format(params, DEFAULT_CHARSET);
		}

		HttpGet httpGet = new HttpGet(urlCopy);
		try {
			return httpClient.execute(httpGet);
		} catch (IOException e) {
			throw new ApiException(url, e);
		}
	}

	public static HttpResponse post(String url, HttpEntity httpEntity) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(httpEntity);

		try {
			return httpClient.execute(httpPost);
		} catch (IOException e) {
			throw new ApiException(url, e);
		}
	}

	public static HttpResponse post(String url, List<NameValuePair> params) {
		if (params == null) params = new ArrayList<>(0);
		HttpEntity httpEntity = new UrlEncodedFormEntity(params, DEFAULT_CHARSET);
		return post(url, httpEntity);
	}

	public static HttpResponse postJson(String url, String json) {
		StringEntity jsonEntity = new StringEntity(json, ContentType.APPLICATION_JSON.withCharset(DEFAULT_CHARSET));

		return post(url, jsonEntity);
	}

	public static void assertResponseOk(String url, HttpResponse response) {
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ApiException(url, "response status not ok (" + response.getStatusLine() + ")", null);
		}
	}

	public static <T> T parseJsonResponse(String url, HttpResponse response, Class<T> clazz) {
		String responseStr = null;
		try {
			responseStr = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
		} catch (IOException e) {
			throw new ApiException(url, "parse response entity to string exception", e);
		}

		try {
			return ApiUtils.getObjectMapper().readValue(responseStr, clazz);
		} catch (IOException e) {
			throw new ApiException(url, "parse response json to " + clazz.getSimpleName() + " exception", e);
		}
	}

	public static byte[] parseBytesResponse(String url, HttpResponse response) {
		try {
			return EntityUtils.toByteArray(response.getEntity());
		} catch (IOException e) {
			throw new ApiException(url, "parse response entity to byte[] exception", e);
		}
	}

	public static String toJson(Object object) {
		try {
			return getObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String sign(List<NameValuePair> params, String algrithm) {
		return sign(joinSorted(params), algrithm);
	}

	public static String sign(String text, String algrithm) {
		// 作摘要
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(algrithm);
		} catch (NoSuchAlgorithmException e) {
			throw new ApiException(e);
		}
		byte[] digestBytes = digest.digest(text.getBytes(DEFAULT_CHARSET));

		// 转成16进制
		Formatter formatter = new Formatter();
		for (byte b : digestBytes) {
			formatter.format("%02x", b);
		}
		String rt = formatter.toString();
		formatter.close();
		return rt;
	}

	public static String joinSorted(List<NameValuePair> params) {
		String paramStr = params.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).map(e -> e.getName() + "=" + e.getValue()).collect(Collectors.joining("&"));
		return paramStr;
	}

}
