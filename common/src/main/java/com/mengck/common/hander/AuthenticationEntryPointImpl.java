package com.mengck.common.hander;

import com.alibaba.fastjson2.JSON;
import com.mengck.common.response.ResponseResult;
import com.mengck.common.util.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 重写 返回值，将result 封装给到  response
        WebUtil.renderString(response,401, JSON.toJSONString(ResponseResult.fail(401,"用户认证失败，请重新登录！")));
    }
}
