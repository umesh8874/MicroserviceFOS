package com.AN1D.an1d.Service;

import com.AN1D.an1d.Repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class couponService {

    @Autowired
    private CouponRepository cr;
}
