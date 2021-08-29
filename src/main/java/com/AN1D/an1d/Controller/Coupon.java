package com.AN1D.an1d.controller;

import com.AN1D.an1d.DTO.CouponDTO;
import com.AN1D.an1d.DTO.Menu;
import com.AN1D.an1d.Repository.MenuRepository;
import com.AN1D.an1d.Service.couponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/coupon")
public class Coupon {
    @Autowired
    private couponService cs;
    @Autowired
    private MenuRepository mr;
    @RequestMapping(path = "/couponDetail",produces = MediaType.APPLICATION_JSON_VALUE)
    public CouponDTO CouponDetail(){

        return new CouponDTO();
    }

    @PostMapping (path = "/postCoupon", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public CouponDTO postCoupon(CouponDTO c){

        return c;
    }

    @RequestMapping(path = "/menu",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public List<Menu> listAll() {
        List<Menu> menuUtems = mr.findAll();
        return menuUtems;
    }

    @RequestMapping(path = "/countReferenceCodeApplied",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public int referenceCode() {
        List<Menu> menuUtems = mr.findAll();
        return 1;
    }


}
