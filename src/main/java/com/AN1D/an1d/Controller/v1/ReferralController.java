package com.AN1D.an1d.controller.v1;
import org.slf4j.*;
import java.util.List;
import java.util.Map;
import com.AN1D.an1d.DTO.UserReferral;
import com.AN1D.an1d.Exceptions.UnProcessableEntityException;
import com.AN1D.an1d.Service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("v1/")
public class ReferralController {

    @Autowired
    private ReferralService referralService;

    private final Logger LOG = LoggerFactory.getLogger(ReferralController.class);

    @PostMapping("create-user-referral")
    public List<UserReferral> createUserReferral(@RequestParam(value="user_ids") List<Long> user_ids){
        if(user_ids == null || user_ids.isEmpty())
            throw new UnProcessableEntityException("user_ids can't be empty!");
        
        return referralService.createReferrals(user_ids);
    }

    /**
     * Find by either user_id or Referral code
     * @param unique_id
     * @return
     */
    @GetMapping("get-user-referral-info/{unique_id}")
    public ResponseEntity<Object> getUserReferralDetails(@PathVariable("unique_id") String unique_id){
        return referralService.findByUniqueIds(unique_id);
    }

    @PostMapping("update-referral-discount/{user_id}")
    public Map<String, Object> updateReferralDiscountValue(@PathVariable("user_id") int user_id, 
    @RequestParam(value = "access_token", required = true) String access_token){
        referralService.validateRequest(user_id, access_token);
        return referralService.updateReferralValue(user_id);
    }

    // @GetMapping("get-user-referral-info")
    // public String getUserReferralDetails(@RequestParam("user_id") String user_id){
    //     if((user_id == null))
    //         throw new UnProcessableEntityException("user_id can't be empty!");
        
    //     return user_id;
    // }
}