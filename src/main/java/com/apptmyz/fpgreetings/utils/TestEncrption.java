package com.apptmyz.fpgreetings.utils;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TestEncrption {

	private static final String ALGO = "AES"; // Default uses ECB PKCS5Padding

	public static String encrypt(String Data, String secret) throws Exception {
		Key key = generateKey(secret);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		System.out.println("Encrypted:" + new String(encVal));
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	public static String decrypt(String strToDecrypt, String secret) {
		try {
			Key key = generateKey(secret);
			Cipher cipher = Cipher.getInstance(ALGO);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	private static Key generateKey(String secret) throws Exception {
		byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
		Key key = new SecretKeySpec(decoded, ALGO);
		return key; 
	}

	public static String decodeKey(String str) {
		byte[] decoded = Base64.getDecoder().decode(str.getBytes());
		return new String(decoded);
	}

	public static String encodeKey(String str) {
		byte[] encoded = Base64.getEncoder().encode(str.getBytes());
		return new String(encoded);
	}

	public static void main(String a[]) throws Exception {
		
		String secretKey = "birthdaywishes22";
		String encodedBase64Key = encodeKey(secretKey);
		System.out.println("EncodedBase64Key = " + encodedBase64Key); // This need to be share between client and server

		String toEncrypt = "bhaskar@tapits.in";
		System.out.println("Plain text = " + toEncrypt);

		// AES Encryption based on above secretKey
		String encrStr = TestEncrption.encrypt(toEncrypt, encodedBase64Key);
		System.out.println("Cipher Text: Encryption of str = " + encrStr);
		System.out.println("Cipher Text: Base64  Encryption of str = " + Base64.getEncoder().encodeToString(encrStr.getBytes()));
		


		// AES Decryption based on above secretKey
		String decrStr = TestEncrption.decrypt(encrStr, encodedBase64Key);
		System.out.println("Decryption of str = " + decrStr);
	}
}
