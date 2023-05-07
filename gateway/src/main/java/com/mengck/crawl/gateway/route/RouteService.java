package com.mengck.crawl.gateway.route;

import org.springframework.cloud.gateway.route.RouteDefinition;

public interface RouteService {

    void update(RouteDefinition routeDefinition);

    void add(RouteDefinition routeDefinition);
}