package com.example.springboot_security_review.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/v1/member/**", "/v1/board/write").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // ROLE_는 강제성이 있음.
//                .antMatchers("/v1/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutUrl("/v1/auth/logout")
                .and()
                .formLogin() // x-www-form-urlencoded 로 전송 (Json으로 던지면 못 받음)
                .loginPage("/v1/auth/login")
                .loginProcessingUrl("/v1/auth/login")// 스프링 시큐리티가 /login 주소가 들어오면 낚아챔
                .defaultSuccessUrl("/v1/board");// x-www-urlencoded (어느 경로로 갈려고하는데 인증에 막히면 인증 후 그 경로로 감)

        return http.build();
    }


}
