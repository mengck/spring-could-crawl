package com.mengck.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil {


    @Autowired
    private RedisTemplate redisTemplate;


    public void set(String key,String value){

        redisTemplate.boundValueOps(key).set(value);
    }


    public String get(String key){
        Object o = redisTemplate.boundValueOps(key).get();
        if(null ==o ){
            return null;
        }else{
            return o.toString();
        }
    }
}
