package com.xjd.wechat.web.auth;

import org.junit.Test;

import com.xjd.wechat.ApiCommons;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.TestEnv;

/**
 * @author elvis.xu
 * @since 2017-10-30 13:53
 */
public class WebAuthApiTest {
	@Test
	public void getAuthorizeUrl() throws Exception {
		String url = WebAuthApi.getAuthorizeUrl(TestEnv.appId, "https://test-f.wozai4u.com", WebAuthApi.Scope.BASE, "MyState");
		System.out.println(url);
	}

	@Test
	public void getAccessToken() throws Exception {
		AccessToken accessToken = WebAuthApi.getAccessToken(TestEnv.appId, TestEnv.appSecret, "061o4mQa0jARlt1B2jQa0PT9Qa0o4mQX");
		System.out.println(ApiUtils.toJson(accessToken));
	}

	@Test
	public void refreshAccessToken() throws Exception {
		AccessToken accessToken = WebAuthApi.refreshAccessToken(TestEnv.appId, "wQe4vz3hEsPUhrPAjnHsyrFEBQua2uXuu0D6AfAZrqCMGgY5FWm5XqlylXsyvMOhCbZSI-7nHlBDL2cljEsLU6y5TOHD5HOyg6qAeGfZl7I");
		System.out.println(ApiUtils.toJson(accessToken));
	}

	@Test
	public void getUserInfo() throws Exception {
		UserInfo rt = WebAuthApi.getUserInfo("NNgpaoK3fSDR7HsrSyVB3oX1R5fe8eJDa1ssxNqHIuQkvNJ6EBfHlM21uaPMOxyBX84fvq10sYIkxP5i124IbAA1MwmXm3SHR0RzAPkLhjg",
				"oWTCBwTUNV7P0MZhyWUkI8xhL-KU", ApiCommons.WLang.ZH_CN);
		System.out.println(ApiUtils.toJson(rt));
	}
}