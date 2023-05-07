package com.mengck.crawl.user.controller;

import com.mengck.common.domain.User;
import com.mengck.common.response.ResponseResult;
import com.mengck.crawl.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        String token = userService.login(user.getUserName(), user.getPassword());

        Map<String,String> tokenMap = new HashMap<>();

        tokenMap.put("token",token);

        return new ResponseResult(tokenMap);
    }
}
