package com.AN1D.an1d.Utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.*;
import java.util.Random;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import com.AN1D.an1d.Exceptions.UnProcessableEntityException;
import com.AN1D.an1d.config.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

public class CommonUtils {

    private final static Logger LOG = LoggerFactory.getLogger(CommonUtils.class);
    
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

    /**
     * Create Directory
     */
    public static String checkFolderExists(String ex_folder_path) {
        File folder_name = new File(ex_folder_path);
        if (!folder_name.isDirectory()) {
            LOG.info("Folder does not exists creating new folder with name : " + ex_folder_path);
            folder_name.mkdir();
            folder_name.setReadable(true);
            folder_name.setWritable(true);
            folder_name.setExecutable(true);
        } else {
            LOG.info("Folder already exists with name : " + ex_folder_path);
        }
        return ex_folder_path;
    }

    public static String dumpUploadFileOnLocalSystem(String fileLocation, MultipartFile file){
        checkFolderExists(fileLocation);
        String date = DateUtils.getCurretDateAsTimeStamp().toString();
        String fileName = date.replaceAll(" ", "_")+"_"+file.getOriginalFilename();
        String orgFileName = fileLocation+fileName;
        try {
            Files.copy(file.getInputStream(), Paths.get(orgFileName), StandardCopyOption.REPLACE_EXISTING);
            return orgFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getrandomNumber7Digit(){
        Random random = new Random();
        int number = random.nextInt(9999999);
        return String.format("%07d", number);
    }
}
