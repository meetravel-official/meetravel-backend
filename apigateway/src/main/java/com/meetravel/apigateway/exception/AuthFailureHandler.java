package com.meetravel.apigateway.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetravel.apigateway.exception.dto.ErrorCode;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.NoArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class AuthFailureHandler {

    public Mono<Void> formatResponse(ServerHttpResponse response, ErrorCode errorCode) {
        response.getHeaders()
                .add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder json = new StringBuilder();
        try {
            json.append(mapper.writeValueAsString(errorCode));
        } catch (JsonProcessingException jsonProcessingException) {
        }

        String responseBody = json.toString();
        byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory()
                .wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }
}
