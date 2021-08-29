package com.AN1D.an1d.Service;
import java.util.Map;
import org.json.JSONObject;
import com.AN1D.an1d.DTO.UserInfo;
import com.AN1D.an1d.DTO.UserReferral;
import com.AN1D.an1d.Utils.BycryptUtils;
import com.AN1D.an1d.Utils.CommonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.AN1D.an1d.Repository.UserInfoDao;
import com.AN1D.an1d.Repository.UserReferralDao;
import com.AN1D.an1d.Exceptions.UnAuthorisedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SingupService {

    private static int EMAIL_LOGIN = 0;
    private static int MOBILE_LOGIN = 1;
    private static int REFERRAL_LOGIN = 2;
    
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired 
    private UserReferralDao userReferralDao;

    public ResponseEntity<Map<String, Object>> login(String user_name, String password, int type) {
        loginRequestValidator(user_name, password, type);
        UserInfo user = null;
        JSONObject resultSet = null;
        if(type == EMAIL_LOGIN){
            user = userInfoDao.findByEmail(user_name);
        }else if(type == MOBILE_LOGIN){
            user = userInfoDao.findByMobile(user_name);
        }else if(type == REFERRAL_LOGIN){
            UserReferral user_referral = userReferralDao.findByReferralCode(user_name);
            user = userInfoDao.findById(user_referral.getUserId());
        }

        if(user != null){
            String saved_pwd = user.getPassword();
            boolean isPwdValidated = BycryptUtils.validatebCryptHash(password, saved_pwd);
            if(!isPwdValidated)
                throw new UnAuthorisedException("Bad credentials!");
            
            UserReferral user_referral = userReferralDao.findByUserId(user.getUserId());

            resultSet = new JSONObject();
            resultSet.put("user", user);
            resultSet.put("user_referral", user_referral);
        }else{
            throw new UnAuthorisedException("Bad credentials!");
        }

        return new ResponseEntity<>(resultSet.toMap(), HttpStatus.OK);
    }

    private void loginRequestValidator(String user_name, String password, int type){
        if(type == EMAIL_LOGIN){
            if(user_name.endsWith(".com") && user_name.contains("@"))
                CommonUtils.validateEmail(user_name);
        }else if(type == MOBILE_LOGIN){
            if(user_name.toString().trim().length() != 10 && !user_name.matches("[0-9]+"))
                throw new UnAuthorisedException("Bad credentials!");
        }else if(type == REFERRAL_LOGIN){
            if(!user_name.subSequence(0, 5).toString().trim().contains("AHUJA") && user_name.trim().length() != 12)
                throw new UnAuthorisedException("Bad credentials!");
        }
    }
}