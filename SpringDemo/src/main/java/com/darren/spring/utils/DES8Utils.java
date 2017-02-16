package com.darren.spring.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES8Utils {
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static String encrypt(String encryptString, String encryptKey) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
		return Base64Utils.encode(encryptedData);
	}

	public static String decrypt(String decryptString, String decryptKey) throws Exception {
		byte[] byteMi = Base64Utils.decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData, "utf-8");

	}

	public static void main(String[] args) {
		String text = "明文密文";
		String key = RequestClient.getCode("10001");

		try {
			String encryptDES = encrypt(text, key);
			String decryptDES = decrypt(encryptDES, key);
//			System.out.println("明文：" + text);
//			System.out.println("秘钥：" + key);
//			System.out.println("密文：" + encryptDES);
//			System.out.println("解密：" + decryptDES);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
