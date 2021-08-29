package com.AN1D.an1d.Utils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import com.AN1D.an1d.Exceptions.UnProcessableEntityException;
import com.AN1D.an1d.config.Constants;

import org.springframework.http.HttpStatus;

public class CommonUtils {
    
    public static String generateReferral() {
        UUID uuid = UUID.randomUUID();
        return Constants.ORDER_REFRREL_PREFIX+uuid.toString().substring(0, 7).toUpperCase();
    }

    public static String removeBrackets(String str){
        return str.replaceAll("[\\[\\](){}]", "").trim();
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
           InternetAddress emailAddr = new InternetAddress(email);
           emailAddr.validate();
        } catch (AddressException ex) {
           result = false;
        }
        return result;
    }

    public static void validateEmail(String email){
        if(!isValidEmailAddress(email) || !email.endsWith(".com"))
            throw new UnProcessableEntityException("email address is not valid!");
    }

    public static Map<String, Object> customResponseMsg(HttpStatus httpStatus, String type, String message) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("status", httpStatus);
		data.put("type", type);
		data.put("message", message);
		data.put("timestamp", new Date());
		return data;
	}
}
