package com.AN1D.an1d.Service;
import org.slf4j.*;
import java.util.List;
import com.AN1D.an1d.DTO.Sms;
import com.AN1D.an1d.DTO.SmsLog;
import com.AN1D.an1d.DTO.UserInfo;
import com.AN1D.an1d.Repository.SmsLogRepository;
import com.AN1D.an1d.Repository.UserInfoDao;
import com.AN1D.an1d.Repository.impl.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    
    private final SmsSender smsSender;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private SmsLogRepository smsLogRepository;

    @Autowired
    public SmsService(@Qualifier("twilio") SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    private final static Logger LOG = LoggerFactory.getLogger(SmsService.class);

    public void sendSms(Sms smsRequest) {
        int is_bulk = 0;
        smsSender.sendSms(is_bulk, smsRequest);
    }

    public Page<SmsLog>  getSmsLogList(PageRequest page_request){
        Page<SmsLog> smsLogs = smsLogRepository.findAll(page_request);
        return smsLogs;
    }

    @Async("asyncExecutor")
    public void sendBulkSms(String message) {
        int is_bulk = 1;
        List<UserInfo> users = userInfoDao.findAllNonDeleted();
        LOG.info("Total User for sending bulk message => "+ users.size());
        for(UserInfo user : users){
            String country_code = user.getCountryCode().toString().trim();
            String mobile_number = user.getMobile().toString().trim();
            Sms sms = new Sms(country_code, mobile_number, message);
            smsSender.sendSms(is_bulk, sms);
        }
    }
}
