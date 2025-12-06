package com.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${authorization.service.url:http://authorization:8081}")
    private String authorizationServiceUrl;

        @Value("${consumer.service.url:http://authorization:8082}")
    private String consumerServiceUrl;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("authorization", r -> r
                .path("/authorization/**")
                .filters(f -> f.rewritePath("/authorization/(?<segment>.*)", "/${segment}"))
                .uri(authorizationServiceUrl))
            .route("consumer", r -> r
                .path("/consumer/**")
                .filters(f -> f.rewritePath("/consumer/(?<segment>.*)", "/${segment}"))
                .uri(consumerServiceUrl))
            .build();
    }
}