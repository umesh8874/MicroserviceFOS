package com.AN1D.an1d.controller.v1;
import java.util.Map;
import com.AN1D.an1d.Service.UserService;
import com.AN1D.an1d.Utils.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestValidator requestValidator;

    @GetMapping(value = "get-user/{user_id}")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestParam(value = "access_token", required = true) 
        String access_token, @RequestParam(value = "type", required = true) int type,
        @PathVariable(value = "user_id", required = true) Integer user_id){

        requestValidator.tokenValidator(access_token, type);
        
        return userService.getUser(user_id);
    }
}