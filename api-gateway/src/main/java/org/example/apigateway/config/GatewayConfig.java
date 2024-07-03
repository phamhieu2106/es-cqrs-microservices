package org.example.apigateway.config;

import org.example.apigateway.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthFilter authFilter;

    @Autowired
    public GatewayConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/v1/**")
                        .uri("lb://AUTH-SERVER"))
                .route("user-command", r -> r.path("/api/users/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://USER-COMMAND"))
                .route("user-query", r -> r.path("/api/users/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://USER-COMMAND"))
                .route("insurance-command", r -> r.path("/api/insurances/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://INSURANCE-COMMAND"))
                .route("insurance-query", r -> r.path("/api/insurances/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://INSURANCE-QUERY"))
                .route("customer-command", r -> r.path("/api/customers/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://CUSTOMER-COMMAND"))
                .route("customer-query", r -> r.path("/api/customers/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://CUSTOMER-QUERY"))
                .route("contract-command", r -> r.path("/api/contracts/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://CONTRACT-COMMAND"))
                .route("contract-query", r -> r.path("/api/contracts/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://CONTRACT-QUERY"))
                .build();
    }
}
