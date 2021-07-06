package com.AN1D.an1d.Controller;

import com.AN1D.an1d.Service.couponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/coupon")
public class Coupon {
    @Autowired
    private couponService cs;
    @RequestMapping(path = "/couponDetail",produces = MediaType.APPLICATION_JSON_VALUE)
    public com.AN1D.an1d.DTO.Coupon CouponDetail(){
        return new com.AN1D.an1d.DTO.Coupon();
    }

    @PostMapping (path = "/postCoupon", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public com.AN1D.an1d.DTO.Coupon postCoupon(com.AN1D.an1d.DTO.Coupon c){
        return c;
    }


}
