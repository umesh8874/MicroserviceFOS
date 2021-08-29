package com.AN1D.an1d.Utils;
import com.AN1D.an1d.DTO.Users;
import com.AN1D.an1d.Repository.UsersDao;
import com.AN1D.an1d.Exceptions.UnAuthorisedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RequestValidator {

    @Autowired
    private UsersDao usersDao;
    
    public void tokenValidator(String access_token, int type){
        if(access_token == null)
            throw new UnAuthorisedException("access_token is not valid!");

        if((type != 1) && (type != 2))
            throw new UnAuthorisedException("type is not valid!");
        
        Users user_detail = usersDao.findByAccessToken(access_token);;
        if(user_detail == null || user_detail.getId() <= 0)
            throw new UnAuthorisedException("Bad Credentials!");
    }
}