package com.AN1D.an1d.controller.v1;
import java.util.HashMap;
import java.util.Map;
import com.AN1D.an1d.Exceptions.NotFoundException;
import com.AN1D.an1d.Service.UserService;
import com.AN1D.an1d.Utils.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;

@Validated
@RestController
@RequestMapping("v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestValidator requestValidator;

    @GetMapping(value = "get-user/{user_id}")
    public ResponseEntity<Map<String, Object>> getUserInfo(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,
        @PathVariable(value = "user_id", required = true) Integer user_id)
    {
        requestValidator.tokenValidator(access_token, type);
        return userService.getUser(user_id);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Map<String, Object>> retrieveAllUsers(
        @RequestParam(value = "access_token", required = true) String access_token,
        @RequestParam(value = "type", required = true) int type,
        @RequestParam(value = "offset", defaultValue = "0", required = true) int offset,
        @RequestParam(value = "limit", defaultValue = "20", required = true) int limit)
    {
        Map<String, Object> users = new HashMap<String, Object>();
        requestValidator.tokenValidator(access_token, type);

        PageRequest page_request = PageRequest.of(offset, limit, Sort.Direction.DESC, "created_at");
        users = userService.findAllUsers(page_request);

        if(!users.isEmpty()){
            return new ResponseEntity<Map<String,Object>>(users, HttpStatus.OK);
        }else {
			throw new NotFoundException("Users not found!");
		}
    }
}