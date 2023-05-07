package com.mengck.crawl.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan({"com.mengck.common","com.mengck.crawl.task"})
public class CrawlTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlTaskApplication.class,args);
    }
}
