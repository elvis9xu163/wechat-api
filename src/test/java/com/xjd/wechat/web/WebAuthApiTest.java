package com.xjd.wechat.web;

import org.junit.Test;

import com.xjd.wechat.ApiCommons;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.TestEnv;
import com.xjd.wechat.web.auth.AccessToken;
import com.xjd.wechat.web.auth.UserInfo;
import com.xjd.wechat.web.auth.WebAuthApi;

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
		AccessToken accessToken = WebAuthApi.getAccessToken(TestEnv.appId, TestEnv.appSecret, "061OgHnI0Nis3k24x6nI0ZnCnI0OgHnM");
		System.out.println(ApiUtils.toJson(accessToken));
	}

	@Test
	public void refreshAccessToken() throws Exception {
		AccessToken accessToken = WebAuthApi.refreshAccessToken(TestEnv.appId, "wQe4vz3hEsPUhrPAjnHsyrFEBQua2uXuu0D6AfAZrqCMGgY5FWm5XqlylXsyvMOhCbZSI-7nHlBDL2cljEsLU6y5TOHD5HOyg6qAeGfZl7I");
		System.out.println(ApiUtils.toJson(accessToken));
	}

	@Test
	public void getUserInfo() throws Exception {
		UserInfo rt = WebAuthApi.getUserInfo("DOop1Jp7AuG7K1hb7MwHjWDQDmsTJrJ2SfE_j9XGldcWfUY69OpAf8zUwgjMwzIVJvNShe6SDV-bCYyvofJKFeoDruB9FKSE3IwvuGKBEoc",
				"xxxx", ApiCommons.WLang.ZH_CN);
		System.out.println(ApiUtils.toJson(rt));
	}
}