package com.xjd.wechat.escrow.api.component;

import org.junit.Test;

import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.TestEnv;

/**
 * @author elvis.xu
 * @since 2018-04-12 19:03
 */
public class ComponentApiTest {
	@Test
	public void getComponentAccessToken() throws Exception {
		ComponentAccessToken componentAccessToken = ComponentApi.getComponentAccessToken(TestEnv.componentAppId,
				TestEnv.componentAppSecret,
				"ticket@@@aGenXLbbla4-5CGXmUTbLo4psbRM_KQFQ6lF2t1aBkNgBZREIyaduv8cWyQB3jKhQXMYaRrBDPntXLp_XXOK2w");

		System.out.println(ApiUtils.toJson(componentAccessToken));
	}

}