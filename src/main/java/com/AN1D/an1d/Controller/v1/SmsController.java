package com.AN1D.an1d.controller.v1;
import org.apache.commons.lang3.StringUtils;
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
import com.AN1D.an1d.Exceptions.BadRequestException;
import com.AN1D.an1d.Service.SmsService;
import com.AN1D.an1d.Utils.CommonUtils;
import com.AN1D.an1d.Utils.RequestValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Umesh.Shukla
 */

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
    public Page<SmsLog> sendSms(@RequestParam(value = "access-token", required = true) String access_token,
        @RequestParam(value = "offset", defaultValue = "0", required = true) int offset,
        @RequestParam(value = "limit", defaultValue = "20", required = true) int limit)
    
    {
        requestValidator.admin_token_validate(access_token);
        PageRequest page_request = PageRequest.of(offset, limit, Sort.Direction.DESC, "createdAt");
        return smsService.getSmsLogList(page_request);
    }

    @PostMapping(value = "send-bulk-sms")
    public ResponseEntity<Object> sendBulkSms(@RequestParam(value = "access-token", required = true) String access_token,
        @RequestParam(value = "message", required = true) String message)
    {
        requestValidator.admin_token_validate(access_token);
        if(!StringUtils.isNotBlank(message))
            throw new BadRequestException("Message can't be null!");
        smsService.sendBulkSms(message);
        return new ResponseEntity<Object>(CommonUtils.customResponseMsg(HttpStatus.OK, "send-bulk-sms", "Requests submitted successfully."), HttpStatus.OK);
    }
}