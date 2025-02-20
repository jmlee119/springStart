package com.study.boardPage.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean //빈은 스프링에 의해 생성 또는 관리되는 객체
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 내부적으로 SecurityFilterChain 클래스가 동작하여 모든 요청 URL에 이 클래스가 필터로 적용되어 URL별로 특별한 설정을 함
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .csrf((csrf) ->csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/**")));
        //모든 URL은 CSRF 검증을 하지 않는다는 설정을 추가
    return http.build();
    }
}
