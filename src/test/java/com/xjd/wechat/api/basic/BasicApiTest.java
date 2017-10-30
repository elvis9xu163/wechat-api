package com.xjd.wechat.api.basic;

import org.junit.Test;

import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.TestEnv;

/**
 * @author elvis.xu
 * @since 2017-10-30 15:24
 */
public class BasicApiTest {
	@Test
	public void getAccessToken() throws Exception {
		AccessToken entity = BasicApi.getAccessToken(TestEnv.appId, TestEnv.appSecret);
		System.out.println(ApiUtils.toJson(entity));
	}

}