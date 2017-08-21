package com.xjd.wechat.user_auth;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.xjd.wechat.TestEnv;

/**
 * @author elvis.xu
 * @since 2017-08-15 19:05
 */
public class UserAccessTokenApiTest {

	@Test
	public void testGetAccessToken() throws Exception {
		int[] i = {0};
		UserAccessTokenApi.getAccessToken(TestEnv.appId, TestEnv.appSecret, "081Y3lV4064DrI1OevV40bIpV40Y3lVL", new UserAccessTokenApi.UserAccessTokenCallback() {
			@Override
			public void onSuccess(String accessToken, int expiresInSeconds, String refreshToken, String openId, String unionId, String scope) {
				System.out.println("accessToken: " + accessToken);
				System.out.println("expiresInSeconds: " + expiresInSeconds);
				System.out.println("refreshToken: " + refreshToken);
				System.out.println("openId: " + openId);
				System.out.println("unionId: " + unionId);
				System.out.println("scope: " + scope);
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

	@Test
	public void refreshAccessToken() throws Exception {
		int[] i = {0};
		UserAccessTokenApi.refreshAccessToken(TestEnv.appId, "CZ-6V7184oO5X6Ej7W55DEEi5AOrDBlbbsWIOohur2dkH_YoQwaf8lo0dpCTSpvrjDF954edNLVjMXCrHV0Otf1XfTp-NhrQjt4Uedx1ZPU", new UserAccessTokenApi.UserAccessTokenCallback() {
			@Override
			public void onSuccess(String accessToken, int expiresInSeconds, String refreshToken, String openId, String unionId, String scope) {
				System.out.println("accessToken: " + accessToken);
				System.out.println("expiresInSeconds: " + expiresInSeconds);
				System.out.println("refreshToken: " + refreshToken);
				System.out.println("openId: " + openId);
				System.out.println("unionId: " + unionId);
				System.out.println("scope: " + scope);
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

	@Test
	public void checkAccessToken() throws Exception {
		boolean valid = UserAccessTokenApi.checkAccessToken("jp2EfbOvNB5a_t2BbxRJbkunIRDeJeWESBQM4K8eTAKnEYvpFXrUoobIsJmoP81KliQZclL4G4ykipp9EgR4Sgl3iKbAmTjYF2fJJvozKZQ", "oWTCBwWqXFntRgkfsGDmKgj7KvjY");
		Assertions.assertThat(valid).isEqualTo(true);
	}





}