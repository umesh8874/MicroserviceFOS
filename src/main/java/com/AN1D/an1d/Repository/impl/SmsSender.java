package com.AN1D.an1d.Repository.impl;
import com.AN1D.an1d.DTO.Sms;

public interface SmsSender {
    
    void sendSms(Sms smsRequest);

}
