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
		UserUserInfoApi.getUserInfo("7jse5QP8XYJntr_LjD61SZiXNazLsiTGo8z_vlz0_3o1K9vSMCybT9qQDH-IvQ2vmLnlNmlTgGuJVsm6bNlpGQ", "oV6-1wgt0XZkMYl_6Z5O77G0USJo", "zh_CN", new UserUserInfoApi.UserUserInfoCallback() {
			@Override
			public void onSuccess(UserUserInfoApi.UserUserInfoEntity entity) {
				System.out.println(ApiUtils.toJson(entity));
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