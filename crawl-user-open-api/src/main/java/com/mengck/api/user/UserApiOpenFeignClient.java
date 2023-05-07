package com.mengck.api.user;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "crawl-user")
public interface UserApiOpenFeignClient {


}
