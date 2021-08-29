package com.AN1D.an1d.Service;
import java.util.Map;
import com.AN1D.an1d.DTO.UserInfo;
import com.AN1D.an1d.DTO.UserReferral;
import com.AN1D.an1d.Repository.UserInfoDao;
import com.AN1D.an1d.Repository.UserReferralDao;
import com.AN1D.an1d.Utils.BycryptUtils;
import com.AN1D.an1d.Utils.CommonUtils;
import com.AN1D.an1d.Utils.DateUtils;
import com.AN1D.an1d.Utils.Md5Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import com.AN1D.an1d.Exceptions.AlreadyPresentException;
import com.AN1D.an1d.Exceptions.NotFoundException;
import com.AN1D.an1d.Exceptions.UnProcessableEntityException;

@Service
@Validated
public class UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired 
    private UserReferralDao userReferralDao;

    @Value("${default.referral.discount.value}")
	private double with_referral_discount_value;

    @Value("${incr.referral.discount.value}")
	private double without_referral_discount_value;

    @Value("${max.referral.discount.value}")
    private double max_referral_value;

    public ResponseEntity<Map<String, Object>> createUser(MultiValueMap<String, String> user_data) {
        Object first_name = user_data.get("first_name");
        Object last_name = user_data.get("last_name");
        Object email = user_data.get("email");
        Object password = user_data.get("password");
        Object mobile = user_data.get("mobile");
        Object address = user_data.get("address");
        Object consumed_referral_code = user_data.get("consumed_referral_code");

        UserInfo userExists = userInfoDao.findByEmail(CommonUtils.removeBrackets(email.toString().trim()));
        if(userExists == null)
            userExists = userInfoDao.findByMobile(CommonUtils.removeBrackets(mobile.toString().trim()));

        if(userExists != null)
            throw new AlreadyPresentException("User Already exists, please try to login!");

        UserInfo user = new UserInfo();
        user.setFirstName(CommonUtils.removeBrackets(first_name.toString().trim()));
        if(last_name != null)
            user.setLastName(CommonUtils.removeBrackets(last_name.toString().trim()));
        user.setEmail(CommonUtils.removeBrackets(email.toString().trim()));
        user.setPassword(BycryptUtils.bCryptHash(CommonUtils.removeBrackets(password.toString().trim())));
        user.setAccessToken(Md5Util.md5Hashing(CommonUtils.removeBrackets(password.toString().trim())));
        user.setMobile(CommonUtils.removeBrackets(mobile.toString().trim()));
        if(address != null)
            user.setAddress(CommonUtils.removeBrackets(address.toString().trim()));
        UserInfo created_user = userInfoDao.save(user);
        
        UserReferral create_referral = new UserReferral();
        String referral_code = CommonUtils.generateReferral();
        create_referral.setUserId(created_user.getUserId());
        create_referral.setUserReferralCode(referral_code);

        if(consumed_referral_code != null){
            String consumed_referral = CommonUtils.removeBrackets(consumed_referral_code.toString().trim());
            if(consumed_referral.length() == 12){
                create_referral.setConsumedReferralCode(consumed_referral);
                create_referral.setDiscountValue(with_referral_discount_value);
            }
        }else{
            create_referral.setDiscountValue(without_referral_discount_value);
        }
        create_referral.setValidity(DateUtils.getReferralExpiryDate());

        UserReferral created_user_referral = userReferralDao.save(create_referral);
        if(created_user_referral == null)
            throw new UnProcessableEntityException("User referral not created!");
        
        JSONObject resultSet = null;
        if(created_user != null){
            resultSet = new JSONObject();
            resultSet.put("user", created_user);
            resultSet.put("user_referral", created_user_referral);
        }

        return new ResponseEntity<>(resultSet.toMap(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getUser(int user_id) {
        UserInfo user = null;
        JSONObject resultSet = null;
        try{
            user = userInfoDao.findById(user_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(user != null && user.getUserId() == user_id){
            UserReferral user_referral = userReferralDao.findByUserId(user.getUserId());
            resultSet = new JSONObject();
            resultSet.put("user", user);
            resultSet.put("user_referral", user_referral);
        }else{
            throw new NotFoundException("user_id : "+user_id+" User not found!");
        }
        return new ResponseEntity<>(resultSet.toMap(), HttpStatus.OK);
    }
    /**
     * Validate User creation data 
     * @param user_data
     */
    public void validateCreateUserPostData(MultiValueMap<String, String> user_data) {
        Object first_name = user_data.get("first_name");
        if(first_name == null)
            throw new UnProcessableEntityException("first_name can't be null!");

        Object email = user_data.get("email");
        if(email == null){
            throw new UnProcessableEntityException("email can't be null!");
        }else{
            String email_address = CommonUtils.removeBrackets(email.toString().trim());
            if(!CommonUtils.isValidEmailAddress(email_address))
                throw new UnProcessableEntityException("email address is not valid!");
        }

        Object password = user_data.get("password");
        if(password == null)
            throw new UnProcessableEntityException("password can't be null!");

        Object mobile = user_data.get("mobile");
        if(mobile == null){
            throw new UnProcessableEntityException("mobile can't be null!");
        }else{
            String mobile_number = CommonUtils.removeBrackets(mobile.toString().trim());
            if(mobile_number.length() != 10)
                throw new UnProcessableEntityException("mobile number is not valid!");
        }
    }
}