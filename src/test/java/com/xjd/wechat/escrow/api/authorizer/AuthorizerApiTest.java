package com.xjd.wechat.escrow.api.authorizer;

import org.junit.Test;

import com.xjd.wechat.ApiResponse;
import com.xjd.wechat.ApiUtils;
import com.xjd.wechat.TestEnv;

/**
 * @author elvis.xu
 * @since 2018-04-12 19:10
 */
public class AuthorizerApiTest {
	private String componentAccessToken =
			"8_yMi5pL3Ct6hlXB1-BYttJu9YYXSfr7qCOiaxptk6TA4_yAhnXl-ZS6XV6G96tZjETdkpiXnwAWr-fXUJ2R2NwsJoTb9o5FAs3pUm0P8j_MPFDnsgKTBmQeRzGIRICmVlxBfZMrS27b105ztpFBUjABAUQJ";
	private String preAuthCode = "preauthcode@@@6WA3zGJTTrcE9_ZlKqff6VMvu0P0-6KqXHLJslcMbU2jQP1xWjyMTHkcl3DFkjtQ";
	private String authCode =
			"queryauthcode@@@EQ19sda5EdTb7OjEWx-e_g1mWuAXw1AWSnA0h3SSZkEbTOodsrNdsi2S4CrfOGiJxyeNUEEgJtE4uuMR6OQbPA";
	private String accessToken = "8_IW_foOcCsdO5VIWuxTLKOE7HoYh7SAU0GcJYF-_xtAGAKxXD2LUoPWuk_7ztStLNS4Cfqs7lzE0TsyLFB6uzxuwtVO02GiCuermX6-37AWixQjC0hUGsnVxS779xMbjyAwMDVcwoq5BGyINAEXDiAHDIBH";
	private String refreshToken = "refreshtoken@@@64fH_9l42GlGAYGMG69lgjpxICAQI_EXUqnAUJZbFDE";

	@Test
	public void getPreAuthCode() throws Exception {
		PreAuthCode preAuthCode = AuthorizerApi.getPreAuthCode(TestEnv.componentAppId, componentAccessToken);
		System.out.println(ApiUtils.toJson(preAuthCode));
	}

	@Test
	public void getAuthUrl() throws Exception {
		String authUrl = AuthorizerApi.getAuthUrl(TestEnv.componentAppId, preAuthCode, "http://vip3.xxx.vvaccess.com",
				3);
		System.out.println(authUrl);
	}

	@Test
	public void authorize() throws Exception {
		AuthorizationInfo authorize = AuthorizerApi.authorize(TestEnv.componentAppId, componentAccessToken, authCode);
		System.out.println(ApiUtils.toJson(authorize));
	}

	@Test
	public void refreshAuthorizerAccessToken() throws Exception {

		AuthorizerAccessToken authorizerAccessToken = AuthorizerApi.refreshAuthorizerAccessToken(TestEnv
						.componentAppId, componentAccessToken, TestEnv.appId, refreshToken);
		System.out.println(ApiUtils.toJson(authorizerAccessToken));
	}

	@Test
	public void getAuthorizerInfo() throws Exception {
		AuthorizerInfo authorizerInfo = AuthorizerApi.getAuthorizerInfo(TestEnv.componentAppId, componentAccessToken,
				TestEnv.appId);
		System.out.println(ApiUtils.toJson(authorizerInfo));
	}

	@Test
	public void getAuthorizerList() throws Exception {
		AuthorizerList authorizerList = AuthorizerApi.getAuthorizerList(TestEnv.componentAppId, componentAccessToken,
				0L, 100);
		System.out.println(ApiUtils.toJson(authorizerList));
	}

	@Test
	public void getAuthorizerOption() throws Exception {
		AuthorizerOption location_report = AuthorizerApi.getAuthorizerOption(TestEnv.componentAppId,
				componentAccessToken, TestEnv.appId,
				"location_report");
		System.out.println(ApiUtils.toJson(location_report));
	}

	@Test
	public void setAuthorizerOption() throws Exception {
		ApiResponse location_report = AuthorizerApi.setAuthorizerOption(TestEnv.componentAppId, componentAccessToken,
				TestEnv.appId,
				"location_report", "1");
		System.out.println(ApiUtils.toJson(location_report));
	}

}