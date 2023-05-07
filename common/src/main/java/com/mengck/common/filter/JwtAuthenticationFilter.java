package com.mengck.common.filter;


import com.alibaba.fastjson2.JSON;
import com.mengck.common.config.LoginUser;
import com.mengck.common.constant.TokenConstants;
import com.mengck.common.domain.User;
import com.mengck.common.util.CacheUtil;
import com.mengck.common.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CacheUtil cacheUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(TokenConstants.AUTHENTICATION);

        if(null ==token){
            filterChain.doFilter(request,response);
            return;
        }

        // 获取token
        // token 解析出用户Id
        // 获取用户信息
        // 存入 SecurityContextHolder
        //   SecurityContextHolder;

        String userId = JwtUtils.getUserId(token);

        String userJson = cacheUtil.get("login:id:"+userId);

        if(userJson == null){
            throw new RuntimeException("用户数据不存在");
        }


        // 获取权限信息封装
        LoginUser user = JSON.parseObject(userJson,LoginUser.class);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request,response);
    }
}
