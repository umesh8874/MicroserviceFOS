package com.AN1D.an1d.Repository.impl;
import com.twilio.type.PhoneNumber;
import com.AN1D.an1d.DTO.Sms;
import com.AN1D.an1d.DTO.SmsLog;
import com.AN1D.an1d.Repository.SmsLogRepository;
import com.AN1D.an1d.config.Constants;
import com.AN1D.an1d.config.Beans.TwilioInitializer;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class SmsServiceImpl implements SmsSender{

    private TwilioInitializer twilioInitializerl;

    @Autowired
    public SmsServiceImpl(TwilioInitializer twilioInitializerl) {
        this.twilioInitializerl = twilioInitializerl;
    }

    @Autowired
    private SmsLogRepository smsLogRepository;

    @Override
    public void sendSms(int is_bulk, Sms smsRequest) {
        TwilioRestClient client = twilioInitializerl.twilioRestClient();
        PhoneNumber to_mobile_number = new PhoneNumber(smsRequest.getCountryCode()+smsRequest.getMobileNumber());
        PhoneNumber from_mobile_number = new PhoneNumber(twilioInitializerl.fromPhoneNumber());
        String message = smsRequest.getMessage();
        MessageCreator creator = Message.creator(to_mobile_number, from_mobile_number, message);
        try{
            creator.create(client);
            Response response = client.getHttpClient().getLastResponse();
            if(response.getStatusCode() == 201){
                SmsLog sms_log = new SmsLog();
                sms_log.setSmsType(Constants.SMS_TYPES[2]);
                sms_log.setCountryCode(smsRequest.getCountryCode());
                sms_log.setMobileNumber(smsRequest.getMobileNumber());
                sms_log.setIsSent((byte) 1);
                smsLogRepository.save(sms_log);
            }
        }catch(Exception e){
            SmsLog sms_log = new SmsLog();
            sms_log.setSmsType(Constants.SMS_TYPES[2]);
            sms_log.setCountryCode(smsRequest.getCountryCode());
            sms_log.setMobileNumber(smsRequest.getMobileNumber());
            sms_log.setIsSent((byte) 0);
            sms_log.setFailureLog(e.getLocalizedMessage());
            smsLogRepository.save(sms_log);
            if(is_bulk == 0)
                throw new IllegalArgumentException("Argument not valid.");
        }
    }
}
