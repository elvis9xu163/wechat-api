package com.xjd.wechat.api.jsapi;

import org.junit.Test;

import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-11-06 14:34
 */
public class JsApiTest {
	@Test
	public void getTicket() throws Exception {
		Ticket ticket = JsApi.getTicket("ngfgj_CqrbB7aWFgtuTNrszlcV_l7WFYXmTXeHo9xbPz6s9JRuBEW_OuWAMgHCbrPRwm59oDjvvdmfAF1kwmyAbDZBE15uBf_nGkGajy4F6G2B2hP3Mb3j50Vbi8CGjTXCFgAGAFKN");
		System.out.println(ApiUtils.toJson(ticket));
	}

	@Test
	public void sign() throws Exception {
		String sign = JsApi.sign("kgt8ON7yVITDhtdwci0qedeiZ84xRBhxssM7E4c59OOFSBdK3sE_jiGQ1ydaakPSsYsyHp5EMQZF3gDtenh-0g", "3dbf1269e56149d08fbe142727914f36", 1509950297, "http://sf.wozai4u.com");
		System.out.println(sign);
	}

}