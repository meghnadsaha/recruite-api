//package com.recruitment.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class SecurityConfig {
//
//    // Define the PasswordEncoder bean
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // Define the SecurityFilterChain for HttpSecurity configuration
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()  // Allow unauthenticated access to login and register
//                        .anyRequest().authenticated()  // All other endpoints require authentication
//                )
//                .formLogin(withDefaults())  // Enable form login with default settings
//                .httpBasic(withDefaults());  // Enable basic HTTP authentication with default settings
//
//        return http.build();
//    }
//}
