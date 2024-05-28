package com.meetravel.apigateway.exception;

import com.meetravel.apigateway.exception.dto.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private final ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

    /**
     * Authentication entry point has commence method when failures occur
     */
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return new AuthFailureHandler().formatResponse(response, errorCode);
    }
}
