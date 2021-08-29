package com.AN1D.an1d.Utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Md5Util {
   
    /**
	 * MD5 Encryption
	 * @param pwdToBeHashed
	 * @return
	 */
	public static String md5Hashing(String pwdToBeHashed) {
		String created_pwd = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] salt = getSalt();
			md.update(salt);
			md.update(pwdToBeHashed.getBytes());
			
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i< bytes.length ;i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			created_pwd = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return created_pwd;
	}
	
	private static byte[] getSalt(){
        SecureRandom secure_random_number;
        byte[] salt = new byte[16];
		try {
			secure_random_number = SecureRandom.getInstance("SHA1PRNG");
			secure_random_number.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt;
    }
}
