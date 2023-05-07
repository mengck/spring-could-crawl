package com.mengck.common.openfeign;

import com.mengck.common.constant.SecurityConstants;
import com.mengck.common.util.WebUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class OpenFeignReqRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = WebUtil.getRequest();

        if(null != request){

            Map<String, String> headers = WebUtil.getHeaders(request);

            if(headers.containsKey(SecurityConstants.AUTHORIZATION_HEADER)){
                requestTemplate.header(SecurityConstants.AUTHORIZATION_HEADER,headers.get(SecurityConstants.AUTHORIZATION_HEADER));
            }
        }
    }
}
