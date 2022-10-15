/*******************************************************************************
 * DISCLAIMER: The sample code or utility or tool described herein
 *    is provided on an "as is" basis, without warranty of any kind.
 *    UIDAI does not warrant or guarantee the individual success
 *    developers may have in implementing the sample code on their
 *    environment. 
 *    
 *    UIDAI does not warrant, guarantee or make any representations
 *    of any kind with respect to the sample code and does not make
 *    any representations or warranties regarding the use, results
 *    of use, accuracy, timeliness or completeness of any data or
 *    information relating to the sample code. UIDAI disclaims all
 *    warranties, express or implied, and in particular, disclaims
 *    all warranties of merchantability, fitness for a particular
 *    purpose, and warranties related to the code, or any service
 *    or software related thereto. 
 *    
 *    UIDAI is not responsible for and shall not be liable directly
 *    or indirectly for any direct, indirect damages or costs of any
 *    type arising out of use or any action taken by you or others
 *    related to the sample code.
 *    
 *    THIS IS NOT A SUPPORTED SOFTWARE.
 ******************************************************************************/
package com.apptmyz.fpgreetings.utils;

import java.security.MessageDigest;
import java.security.Security;
/**
 * A utility class to create SHA-256 hashes
 * 
 * @author UIDAI
 *
 */
public class HashGenerator {

	//static String KEY_AES = "e07ecd01e6fca0b2";
	public byte[] generateSha256Hash(byte[] message) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String algorithm = "SHA-256";
		String SECURITY_PROVIDER = "BC";

		byte[] hash = null;

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm, SECURITY_PROVIDER);
			digest.reset();
			hash = digest.digest(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hash;
	}
//	public static String generateBcrypt(String value) {
//        String bcryptHash = BCrypt.hashpw(value, BCrypt.gensalt());
//        return bcryptHash;
//	}
	
	public static void main(String[] args) {
//		System.out.println(new String(Base64.encode(generateSha256Hash("268928810048".getBytes()))));
	}
	
	/*public static String encrypt(String value) {
	        try {
	            byte[] key = KEY_AES.getBytes("UTF-8");
	            byte[] ivs = KEY_AES.getBytes("UTF-8");
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
	            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivs);
	            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec);
	            return Base64.encode(cipher.doFinal(value.getBytes("UTF-8"))).replace("\r\n", "");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	public static String decrypt(String encrypted) {
        try {
        	byte[] key = KEY_AES.getBytes("UTF-8");
            byte[] ivs = KEY_AES.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivs);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decode(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }*/
}
