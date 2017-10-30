package com.xjd.wechat.api.message.template;

import java.util.Arrays;

import org.junit.Test;

import com.xjd.wechat.ApiUtils;

/**
 * @author elvis.xu
 * @since 2017-10-30 16:34
 */
public class TemplateMsgApiTest {
	@Test
	public void send() throws Exception {
		SendResponse rt = TemplateMsgApi.send("SF1MTsqa_mPE2dMX-MdUUtFk52ulmHddI9xDDRGXlXV_XjEfCZpROq5Bg-t6QrOhXNph9yF2XwyJJKaOow209fL7STJiumhvB_1r7ZQYjVQyJWsosoFlU2lkfqQOcJNFOPOcAFACDC",
				"oWTCBwWqXFntRgkfsGDmKgj7KvjY",
				"NLvp7XCKGqBcYsmxTHoUMupy0jGBLOx6A-PLoNE_RFw",
				"http://www.wozai4u.com",
				Arrays.asList(new TemplateMsgApi.MsgTemplateParam().setKey("first").setValue("报名成功!").setColor("#173177"),
						new TemplateMsgApi.MsgTemplateParam("keynote1", "友爱团结", "#173177"),
						new TemplateMsgApi.MsgTemplateParam("keynote2", "见活动详情", "#173177"),
						new TemplateMsgApi.MsgTemplateParam("keynote3", "见活动详情", "#173177"),
						new TemplateMsgApi.MsgTemplateParam("remark", "感谢您的参与!", "#173177")),
				null);
		System.out.println(ApiUtils.toJson(rt));
	}

}