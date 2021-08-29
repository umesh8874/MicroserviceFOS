package com.AN1D.an1d.Utils;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Used for password hashing
 */
public class BycryptUtils {
    
    /**
	 * Bycrpt generator
	 * @param pwdToBeHashed
	 * @return
	 */
	public static String bCryptHash(String pwdToBeHashed) {
		int logRounds = 13;
		String pwd = BCrypt.hashpw(pwdToBeHashed, BCrypt.gensalt(logRounds));
		return pwd.toString();
	}

	/**
	 * Validate two bcrypt hash
	 * @param pwd String format
	 * @param hashed_pwd saved hash
	 * @return
	 */
	public static boolean validatebCryptHash(String pwd, String hashed_pwd) {
		boolean isValid = BCrypt.checkpw(pwd, hashed_pwd);
		if(!isValid)
           return false;
		return true;
	}
}
