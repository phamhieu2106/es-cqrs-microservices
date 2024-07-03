package org.example.apigateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.userdomain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GatewayFilter {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:9999/auth/v1/internal/validate")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("Unauthorized")))
                .bodyToMono(String.class) // Read response as String
                .flatMap(response -> {
                    try {
                        UserEntity userDetails = objectMapper.readValue(response, UserEntity.class);
                        ServerHttpRequest request = exchange.getRequest().mutate()
                                .header("X-User-Name", userDetails.getUsername())
                                .build();
                        ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();
                        return chain.filter(mutatedExchange);
                    } catch (Exception e) {
                        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                        return exchange.getResponse().setComplete();
                    }
                });
    }
}
