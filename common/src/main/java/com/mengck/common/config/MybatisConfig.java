package com.mengck.common.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@MapperScan("com.mengck.common.mapper")
@Configuration
public class MybatisConfig {

}
