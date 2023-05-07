package com.mengck.crawl.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.common.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mengck.crawl.gateway.route.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

@Component
@Slf4j
@RefreshScope
public class GatewayRouteInitConfig {

    @Autowired
    private GatewayRouteConfigProperties configProperties;

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    @Autowired
    private RouteService routeService;
    /**
     * nacos 配置服务
     */
    @Autowired
    private ConfigService configService;
    /**
     * JSON 转换对象
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        log.info("开始网关动态路由初始化...");
        try {
            // getConfigAndSignListener()方法 发起长轮询和对dataId数据变更注册监听的操作
            // getConfig 只是发送普通的HTTP请求
            String initConfigInfo = configService.getConfigAndSignListener(configProperties.getDataId(), configProperties.getGroup(), nacosConfigProperties.getTimeout(), new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    if (StringUtils.isNotEmpty(configInfo)) {
                        log.info("接收到网关路由更新配置：\r\n{}", configInfo);
                        List<RouteDefinition> routeDefinitions = null;
                        try {
                            routeDefinitions = objectMapper.readValue(configInfo, new TypeReference<List<RouteDefinition>>() {
                            });
                        } catch (JsonProcessingException e) {
                            log.error("解析路由配置出错，" + e.getMessage(), e);
                        }
                        for (RouteDefinition definition : Objects.requireNonNull(routeDefinitions)) {
                            routeService.update(definition);
                        }
                    } else {
                        log.warn("当前网关无动态路由相关配置");
                    }
                }
            });
            log.info("获取网关当前动态路由配置:\r\n{}", initConfigInfo);
            if (StringUtils.isNotEmpty(initConfigInfo)) {
                List<RouteDefinition> routeDefinitions = objectMapper.readValue(initConfigInfo, new TypeReference<List<RouteDefinition>>() {
                });
                for (RouteDefinition definition : routeDefinitions) {
                    routeService.add(definition);
                }
            } else {
                log.warn("当前网关无动态路由相关配置");
            }
            log.info("结束网关动态路由初始化...");
        } catch (Exception e) {
            log.error("初始化网关路由时发生错误", e);
        }
    }

}

