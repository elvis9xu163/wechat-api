package com.xjd.wechat.crypt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.Getter;

import org.apache.commons.codec.binary.Base64;

/**
 * @author elvis.xu
 * @since 2018-04-03 14:53
 */
public class Crypts {
	public static final Charset CHARSET = StandardCharsets.UTF_8;

	/**
	 * 校验签名是否正确
	 *
	 * @param msgSignature
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @param encryptedData
	 * @return
	 */
	public static boolean verifySignature(String msgSignature, String token, String timestamp, String nonce, String
			encryptedData) {
		return getSHA1(token, timestamp, nonce, encryptedData).equals(msgSignature);
	}

	/**
	 * 解密
	 * @param encryptedData
	 * @param encodingAesKey
	 * @return
	 */
	public static DecryptData decrypt(String encryptedData, String encodingAesKey) {
		byte[] original;
		byte[] aesKey = getAesKey(encodingAesKey);
		try {
			// 设置解密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

			// 使用BASE64对密文进行解码
			byte[] encrypted = Base64.decodeBase64(encryptedData);

			// 解密
			original = cipher.doFinal(encrypted);
		} catch (Exception e) {
			throw new CryptException(CryptException.DecryptAESError, e);
		}

		try {
			// 去除补位字符
			byte[] bytes = PKCS7Encoder.decode(original);

			// 分离16位随机字符串,网络字节序和AppId
			byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

			int xmlLength = recoverNetworkBytesOrder(networkOrder);

			String xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
			String fromAppId = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length), CHARSET);

			DecryptData decryptData = new DecryptData();
			decryptData.appId = fromAppId;
			decryptData.content = xmlContent;
			return decryptData;
		} catch (Exception e) {
			throw new CryptException(CryptException.IllegalBuffer, e);
		}
	}

	/**
	 * 解密+验证
	 *
	 * @param msgSignature
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @param encryptedData
	 * @param encodingAesKey
	 * @param appId
	 * @return
	 */
	public static String decryptData(String msgSignature, String token, String timestamp, String nonce, String
			encryptedData, String encodingAesKey, String appId) {
		if (!verifySignature(msgSignature, token, timestamp, nonce, encryptedData)) {
			throw new CryptException(CryptException.ValidateSignatureError);
		}
		DecryptData data = decrypt(encryptedData, encodingAesKey);
		if (!data.appId.equals(appId)) {
			throw new CryptException(CryptException.ValidateAppidError);
		}
		return data.content;
	}

	public static String encrypt(String data, String encodingAesKey, String appId) {
		List<Byte> byteCollector = new ArrayList<>();
		addBytes(byteCollector, getRandomStr().getBytes(CHARSET));
		byte[] bytes = data.getBytes(CHARSET);
		byte[] networkBytes = getNetworkBytesOrder(bytes.length);
		addBytes(byteCollector, networkBytes);
		addBytes(byteCollector, bytes);
		addBytes(byteCollector, appId.getBytes(CHARSET));
		addBytes(byteCollector, PKCS7Encoder.encode(byteCollector.size()));

		try {
			byte[] aesKey = getAesKey(encodingAesKey);
			// 设置加密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

			// 加密
			byte[] encrypted = cipher.doFinal(toBytes(byteCollector));

			// 使用BASE64对加密后的字符串进行编码
			String base64Encrypted = Base64.encodeBase64String(encrypted);

			return base64Encrypted;
		} catch (Exception e) {
			throw new CryptException(CryptException.EncryptAESError, e);
		}
	}


	// AES
	protected static byte[] getAesKey(String aesKey) {
		if (aesKey.length() != 43) {
			throw new CryptException(CryptException.IllegalAesKey);
		}
		return Base64.decodeBase64(aesKey + "=");
	}

	// 生成4个字节的网络字节序
	protected static byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}

	// 还原4个字节的网络字节序
	protected static int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}

	// 随机生成16位字符串
	protected static String getRandomStr() {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	//
	protected static void addBytes(List<Byte> list, byte[] bytes) {
		for (byte aByte : bytes) {
			list.add(aByte);
		}
	}

	//
	protected static byte[] toBytes(List<Byte> list) {
		byte[] bytes = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			bytes[i] = list.get(i);
		}
		return bytes;
	}

	/**
	 * 用SHA1算法生成安全签名
	 *
	 * @param token     票据
	 * @param timestamp 时间戳
	 * @param nonce     随机字符串
	 * @param encrypt   密文
	 * @return 安全签名
	 * @throws CryptException
	 */
	protected static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws CryptException {
		try {
			String[] array = new String[]{token, timestamp, nonce, encrypt};
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			Arrays.sort(array);
			for (int i = 0; i < 4; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CryptException(CryptException.ComputeSignatureError);
		}
	}

	@Getter
	public static class DecryptData {
		String appId;
		String content;
	}


}
