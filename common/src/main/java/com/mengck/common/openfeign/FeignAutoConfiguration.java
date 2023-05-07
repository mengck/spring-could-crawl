package com.mengck.common.openfeign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignAutoConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new OpenFeignReqRequestInterceptor();
    }
}
