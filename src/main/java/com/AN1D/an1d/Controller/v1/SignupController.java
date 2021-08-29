package com.AN1D.an1d.controller.v1;
import java.util.Map;
import javax.validation.Valid;
import com.AN1D.an1d.Service.SingupService;
import com.AN1D.an1d.Service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@Validated
@RestController
@RequestMapping("v1/")
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private SingupService singupService;
    
    @PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> createUser(@RequestParam MultiValueMap<String, String> user_data){
        userService.validateCreateUserPostData(user_data);
        return userService.createUser(user_data);
    }

    @PostMapping(value = "login")
    public ResponseEntity<Map<String, Object>> doLogin(@Valid @RequestParam(value = "user_name", required = true) String user_name, 
        @RequestParam(value = "password", required = true) String password, @RequestParam(value = "type", required = true) int type){

        return singupService.login(user_name, password, type);
    }
}
