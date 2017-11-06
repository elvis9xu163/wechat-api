package com.xjd.wechat.api.user;

import org.junit.Test;

import com.xjd.wechat.ApiCommons;
import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-10-30 15:37
 */
public class UserInfoApiTest {
	@Test
	public void getUserInfo() throws Exception {
		UserInfo entity = UserInfoApi.getUserInfo("SF1MTsqa_mPE2dMX-MdUUnd1ywojVr69jIq7-pr2tjK3MaMwZbdZRWlP-gTfkUtLSaUKk2iTDZ3UCY_SC9eOEnQ9NUqxYxg18XFTRJm-mdS4hSNVPhfZXspXCqzDgMeTXAHeAIABEL",
				"oWTCBwTUNV7P0MZhyWUkI8xhL-KU", ApiCommons.WLang.ZH_CN);
		System.out.println(ApiUtils.toJson(entity));
	}

}