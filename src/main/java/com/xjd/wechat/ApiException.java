package com.xjd.wechat;

/**
 * @author elvis.xu
 * @since 2017-08-15 15:39
 */
public class ApiException extends RuntimeException {

	private String url;

	public ApiException(String url, Throwable cause) {
		this(url, "request exception.", cause);
	}

	public ApiException(String url, String message, Throwable cause) {
		super("[" + url + "] " + message, cause);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
