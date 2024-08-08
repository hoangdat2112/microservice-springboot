//package org.example.micro.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/eureka/**")  // Bỏ qua CSRF cho các yêu cầu đến /eureka/**
//                )
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/eureka/**").permitAll()  // Cho phép tất cả yêu cầu đến /eureka/**
//                                .anyRequest().authenticated()  // Yêu cầu xác thực cho các yêu cầu khác
//                );
//        return httpSecurity.build();
//    }
//}
