package com.xjd.wechat.basic;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.xjd.wechat.TestEnv;

import static com.xjd.wechat.basic.AccessTokenApi.getAccessToken;

/**
 * @author elvis.xu
 * @since 2017-08-15 16:27
 */
public class AccessTokenApiTest {

	@Test
	public void testGetAccessToken() throws Exception {
		final int[] i = {0};
		getAccessToken(TestEnv.appId, TestEnv.appSecret, new AccessTokenApi.AccessTokenCallback() {
			@Override
			public void onSuccess(String accessToken, int expiresInSeconds) {
				System.out.println("accessToken: " + accessToken);
				System.out.println("expiresInSeconds: " + expiresInSeconds);
				i[0] = 1;
			}

			@Override
			public void onFailure(int errCode, String errMsg) {
				System.out.println("errCode: " + errCode);
				System.out.println("errMsg: " + errMsg);
				i[0] = -1;
			}
		});
		Assertions.assertThat(i[0]).isEqualTo(1);
	}
}