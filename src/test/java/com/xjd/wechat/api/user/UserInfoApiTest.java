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
		UserInfo entity = UserInfoApi.getUserInfo("SF1MTsqa_mPE2dMX-MdUUtFk52ulmHddI9xDDRGXlXV_XjEfCZpROq5Bg-t6QrOhXNph9yF2XwyJJKaOow209fL7STJiumhvB_1r7ZQYjVQyJWsosoFlU2lkfqQOcJNFOPOcAFACDC",
				"oWTCBwWqXFntRgkfsGDmKgj7KvjY", ApiCommons.WLang.ZH_CN);
		System.out.println(ApiUtils.toJson(entity));
	}

}