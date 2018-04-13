package com.xjd.wechat.crypt;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import com.xjd.wechat.TestEnv;

/**
 * @author elvis.xu
 * @since 2018-04-03 16:00
 */
public class CryptsTest {
	String appId = TestEnv.componentAppId;
	String token = TestEnv.componentApiToken;
	String aesKey = TestEnv.componentApiKey;
	String msgSignature = "3288204b337b18630a70d45fca832ba084be3c88";
	String timestamp = "1522724657";
	String nonce = "1533761462";
	String encryptedData = "cXJdVfLt1r5s2iNgxkZ4cpeH7xzYmHLiDPXUc3mGr26RnftdrJ0lMzWks25Td43tPKFOl+jUSRduCZPVrGYU0Dl8z3RrTlWkai60dFPbAdlZVTHhrXzztP8O1wB9UINx+33bo5InD9fb9w9s7xFX8EVTXWJ21a5+sx4pbK40L0bNQqkNDRq1Nn3/OH/l3jjaIb3yIIoz3SfAQXjxwmGopy07xEBgmHNC8dmqOlbxoDElqLejkh042z0ikn8tyNSNCixMkbgXo9GzXK4W0U7h6w6JRb4DbjV90f860ZMo2NZ+xO3msu8CJnxioN2bKWAJ8GWtFGCsLc2M0Tz4BVKiAnioMnAc6fCbgGojlG5m7LaaTIWR8SYJbqhv94qOpEelhoSAZe8+PtOh7MpqCxOqhElDdjBsJEdgiTSZdskO3drbO1/Nj3WDJZGqB30wcWlwxIHEfOi4YV4dY2Wr46eAgQ==";
	String decryptedData = "<xml><AppId><![CDATA[wxd304baf747638c40]]></AppId>\n" +
			"<CreateTime>1522724657</CreateTime>\n" +
			"<InfoType><![CDATA[component_verify_ticket]]></InfoType>\n" +
			"<ComponentVerifyTicket><![CDATA[ticket@@@B_RvhDhkFLmSafqZGy8uwt4nCT0pDKa7MQlUkAFU4-mVKWkDkcMi27" +
			"-MmEOOfyuXtsLSn_rTui-ucDCGRV1Q2w]]></ComponentVerifyTicket>\n" +
			"</xml>";


	@Test
	public void verifySignature() throws Exception {
		boolean flag = Crypts.verifySignature(msgSignature, token, timestamp, nonce, encryptedData);
		Assertions.assertThat(flag).isEqualTo(true);
	}

	@Test
	public void decrypt() throws Exception {
		Crypts.DecryptData data = Crypts.decrypt(encryptedData, aesKey);
		System.out.println("======解密后======");
		System.out.println(data.getAppId());
		System.out.println(data.getContent());
		Assertions.assertThat(data.getAppId()).isEqualTo(appId);
		Assertions.assertThat(data.getContent()).isEqualTo(decryptedData);
	}

	@Test
	public void decryptData() throws Exception {
		String content = Crypts.decryptData(msgSignature, token, timestamp, nonce, encryptedData, aesKey, appId);
		Assertions.assertThat(content).isEqualTo(decryptedData);
	}

	@Test
	public void encrypt() throws Exception {
		String encrypt = Crypts.encrypt(decryptedData, aesKey, appId);
		System.out.println("======加密后======");
		System.out.println(encrypt);
		Assertions.assertThat(decryptedData).isEqualTo(Crypts.decrypt(encrypt, aesKey).getContent());
	}

}