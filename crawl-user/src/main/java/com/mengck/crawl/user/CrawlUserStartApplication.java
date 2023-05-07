package com.mengck.crawl.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients(basePackages = "com.mengck.api")
@ComponentScan({"com.mengck.common","com.mengck.crawl.user"})
public class CrawlUserStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlUserStartApplication.class,args);
    }
}
