package com.xjd.wechat.user_auth;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-08-16 10:09
 */
public class UserUserInfoApiTest {
	@Test
	public void getUserInfo() throws Exception {
		int[] i = {0};
		UserUserInfoApi.getUserInfo("jp2EfbOvNB5a_t2BbxRJbkunIRDeJeWESBQM4K8eTAKnEYvpFXrUoobIsJmoP81KliQZclL4G4ykipp9EgR4Sgl3iKbAmTjYF2fJJvozKZQ", "oWTCBwWqXFntRgkfsGDmKgj7KvjY", "zh_CN", new UserUserInfoApi.UserUserInfoCallback() {
			@Override
			public void onSuccess(UserUserInfoApi.UserUserInfoEntity entity) {
				System.out.println(ApiUtils.toJson(entity));
				i[0] = 1;
			}

			@Override
			public void onFailed(int errCode, String errMsg) {
				System.out.println("errCode: " + errCode);
				System.out.println("errMsg: " + errMsg);
				i[0] = -1;
			}
		});
		Assertions.assertThat(i[0]).isEqualTo(1);
	}
}