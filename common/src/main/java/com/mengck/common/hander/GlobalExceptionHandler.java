package com.mengck.common.hander;

import com.mengck.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handerAccessDeniedException(AccessDeniedException accessDeniedException,
                                                      HttpServletRequest request){
        String requestURI = request.getRequestURI();
         log.error(" 请求出错 -- {}",requestURI);
         return ResponseResult.fail(403,accessDeniedException.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseResult handerAuthenticationException(AuthenticationException accessDeniedException,
                                                      HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error(" 请求出错 -- {}",requestURI);
        return ResponseResult.fail(403,accessDeniedException.getMessage());
    }

}
