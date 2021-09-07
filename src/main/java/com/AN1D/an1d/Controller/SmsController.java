package com.AN1D.an1d.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import com.AN1D.an1d.DTO.Sms;
import com.AN1D.an1d.DTO.SmsLog;
import com.AN1D.an1d.Service.SmsService;
import com.AN1D.an1d.Utils.RequestValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("v1/")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    RequestValidator requestValidator;

    @PostMapping(value = "send-sms")
    public void sendSms(@RequestParam(value = "access-token", required = true) String access_token,
        @Valid @RequestBody Sms smsRequest) 
    {
        requestValidator.admin_token_validate(access_token);
        smsService.sendSms(smsRequest);
    }

    @GetMapping(value = "get-send-sms-logs")
    public Page<SmsLog>  sendSms(@RequestParam(value = "access-token", required = true) String access_token,
        @RequestParam(value = "offset", defaultValue = "0", required = true) int offset,
        @RequestParam(value = "limit", defaultValue = "20", required = true) int limit)
    
    {
        requestValidator.admin_token_validate(access_token);
        PageRequest page_request = PageRequest.of(offset, limit, Sort.Direction.DESC, "createdAt");
        return smsService.getSmsLogList(page_request);
    }
}