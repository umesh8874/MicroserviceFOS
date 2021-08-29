package com.AN1D.an1d.Service;
import org.json.JSONObject;
import org.slf4j.*;
import java.util.List;
import java.util.Map;
import com.AN1D.an1d.DTO.UserInfo;
import com.AN1D.an1d.DTO.UserReferral;
import com.AN1D.an1d.Exceptions.BadRequestException;
import com.AN1D.an1d.Exceptions.NotFoundException;
import com.AN1D.an1d.Exceptions.UnAuthorisedException;
import com.AN1D.an1d.Exceptions.UnProcessableEntityException;
import com.AN1D.an1d.Repository.OrderDao;
import com.AN1D.an1d.Repository.UserInfoDao;
import com.AN1D.an1d.Repository.UserReferralDao;
import com.AN1D.an1d.Utils.CommonUtils;
import com.AN1D.an1d.Utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReferralService {

    @Value("${incr.referral.discount.value}")
	private double referral_incr_value;

    @Value("${max.referral.discount.value}")
    private double max_referral_value;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired 
    private UserReferralDao userReferralDao;

    @Autowired
    private OrderDao orderDao;

    private final Logger LOG = LoggerFactory.getLogger(ReferralService.class);

    public List<UserReferral> createReferrals(List<Long> user_ids) {
        int create_count = 0;
        List<UserReferral> userreferralInfo = null;
        List<UserInfo> userInfo = userInfoDao.findByIds(user_ids);

        if(userInfo.size() == 0)
            throw new NotFoundException("Users not found!");

        userreferralInfo = userReferralDao.findByUserIds(user_ids);

        for(UserInfo user : userInfo){
            int isupdate = 0;
            long user_id = Long.parseLong(String.valueOf(user.getUserId()));
            if(userreferralInfo.size() > 0)
                isupdate = isUserReferralPresent(user_id, userreferralInfo);

            if(user_ids.contains(user_id) && isupdate == 0){
                String referral_code = CommonUtils.generateReferral();
                UserReferral userReferral = new UserReferral();
                userReferral.setUserId((int) user_id);
                userReferral.setUserReferralCode(referral_code);
                userReferral.setDiscountValue(10.00);
                userReferral.setValidity(DateUtils.getReferralExpiryDate());
                UserReferral saved_user_ref = userReferralDao.save(userReferral);
                if(saved_user_ref == null)
                    throw new UnProcessableEntityException("User data not able to save for user : "+user_id);
                create_count++;
            }
        }
        if(create_count >= 0)
            userreferralInfo = userReferralDao.findByUserIds(user_ids);
        return userreferralInfo;
    }

    private int isUserReferralPresent(long user_id, List<UserReferral> userreferralInfo){
        for(UserReferral user_referral : userreferralInfo){
            long referral_user_id = Long.parseLong(String.valueOf(user_referral.getUserId()));
            if(referral_user_id == user_id){
                return 1;
            }
        }
        return 0;
    }

    public ResponseEntity<Object> findByUniqueIds(String unique_id) {
        int user_id = 0;
        String referral_code = "";
        UserInfo userInfo = null;
        UserReferral userReferral = null;
        try{
            user_id = Integer.parseInt(unique_id.trim());
        }catch(Exception e){
            referral_code = unique_id;
        }
        if(referral_code.length() > 0){
            userReferral = userReferralDao.findByReferralCode(referral_code);
            if(userReferral != null){
                userInfo = userInfoDao.findById(userReferral.getUserId());
            }else{
                throw new NotFoundException("No data found!");
            }
        }else{
            userInfo = userInfoDao.findById(user_id);
            userReferral = userReferralDao.findByUserId(user_id);
        }
        if(userReferral == null || userInfo == null){
            throw new NotFoundException("No data found!");
        }

        JSONObject respObj = new JSONObject();
        respObj.put("user_details", userInfo);
        respObj.put("user_referral", userReferral);
        return new ResponseEntity<>(respObj.toMap(), HttpStatus.OK);
    }

    public Map<String, Object> updateReferralValue(int user_id) {
        int order_count = orderDao.findByUserId(user_id, 1, 1);
        if(order_count > 1)
            throw new BadRequestException("Not eligible to update referral!");

        UserReferral user_referral = userReferralDao.findByUserId(user_id);
        if(user_referral != null){
            String consumed_referral_code = user_referral.getConsumedReferralCode();
            UserReferral ex_user_referral = userReferralDao.findByReferralCode(consumed_referral_code);

            if(ex_user_referral != null){
                double current_discount = ex_user_referral.getDiscountValue();
                if(ex_user_referral.getConsumedReferralCode() != null){
                    if(current_discount < max_referral_value)
                        ex_user_referral.setDiscountValue(current_discount+referral_incr_value);
                }else{
                    if(current_discount < (max_referral_value-5))
                        ex_user_referral.setDiscountValue(current_discount+referral_incr_value);
                }
                userReferralDao.save(ex_user_referral);
                return CommonUtils.customResponseMsg(HttpStatus.OK, "success", "details updated successfully.");
            }else{
                throw new UnProcessableEntityException("No referred user found!");
            }
        }
        return null;
    }

    public void validateRequest(int user_id, String access_token) {
        UserInfo user = userInfoDao.findById(user_id);
        if(user == null){
            throw new NotFoundException("User not found!");
        }else{
            if(!user.getAccessToken().equals(access_token)){
                throw new UnAuthorisedException("User unauthorized!");
            }
        }
    }
}