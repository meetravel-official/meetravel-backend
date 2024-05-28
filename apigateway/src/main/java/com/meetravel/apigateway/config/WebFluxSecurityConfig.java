package com.meetravel.apigateway.config;

import com.meetravel.apigateway.exception.CustomAccessDeniedHandler;
import com.meetravel.apigateway.exception.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * com.meetravel.apigateway.config.WebFluxSecurityConfig
 * <p>
 * Spring Security Config 클래스
 *
 * @author HyeonminShin
 * @version 1.0
 * @since 2025/05/28
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일           수정자               수정내용
 *  ----------    ------------    ---------------------------
 *  2024/05/28    HyeonminShin    최초 생성
 * </pre>
 */
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    private final static String[] PERMITALL_ANTPATTERNS = {
            "/", "/csrf",
            "/user-service/login",
            "/?*-service/api/v1/messages/**", "/?*-service/actuator/?*", "/actuator/?*",
            "/v3/api-docs/**", "/?*-service/v3/api-docs",
            "**/configuration/*", "/swagger*/**", "/webjars/**"
    };

    /**
     * WebFlux 스프링 시큐리티 설정
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // handlers for 401 and 403
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                                         .accessDeniedHandler(new CustomAccessDeniedHandler()))
                //rest services don't have a login form
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                //disabled basic authentication
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .headers(headers -> headers.frameOptions(ServerHttpSecurity.HeaderSpec.FrameOptionsSpec::disable))
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers(PERMITALL_ANTPATTERNS).permitAll()
                        .anyExchange().authenticated())
        ;
        return http.build();
    }
}
