package com.meetravel.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * com.meetravel.apigateway.ApigatewayApplication
 * <p>
 * 게이트웨이 어플리케이션 클래스
 * Eureka Client 로 설정했기 때문에 Eureka Server 가 먼저 기동되어야 한다.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ApigatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayApplication.class, args);
    }

}
