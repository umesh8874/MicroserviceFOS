package com.AN1D.an1d.Service;
import com.AN1D.an1d.DTO.Sms;
import com.AN1D.an1d.DTO.SmsLog;
import com.AN1D.an1d.Repository.SmsLogRepository;
import com.AN1D.an1d.Repository.impl.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    
    private final SmsSender smsSender;

    @Autowired
    private SmsLogRepository smsLogRepository;

    @Autowired
    public SmsService(@Qualifier("twilio") SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(Sms smsRequest) {
        smsSender.sendSms(smsRequest);
    }

    public Page<SmsLog>  getSmsLogList(PageRequest page_request){
        Page<SmsLog> smsLogs = smsLogRepository.findAll(page_request);
        return smsLogs;
    }
}
