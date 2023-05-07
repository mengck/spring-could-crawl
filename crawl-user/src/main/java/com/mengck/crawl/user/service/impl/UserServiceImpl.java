package com.mengck.crawl.user.service.impl;

import com.alibaba.fastjson2.JSON;
import com.mengck.common.config.LoginUser;
import com.mengck.common.constant.SecurityConstants;
import com.mengck.common.util.CacheUtil;
import com.mengck.common.util.JwtUtils;
import com.mengck.crawl.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CacheUtil cacheUtil;


    @Override
    public String login(String userName, String password) {


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userName,password);

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(Objects.isNull(authenticate)){
            throw new RuntimeException("认证不通过");
        }

        LoginUser principal = (LoginUser) authenticate.getPrincipal();

        // 生成token 给到前端

        Map<String, Object> claims = new HashMap<>();
        Integer id = principal.getUser().getId();
        claims.put(SecurityConstants.DETAILS_USER_ID,principal.getUser().getId());

        String token = JwtUtils.createToken(claims);

        cacheUtil.set("login:id:"+id, JSON.toJSONString(principal));

        return token;

    }
}
