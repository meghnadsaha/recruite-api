package com.recruitment.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/standard/**").hasRole("STANDARD")
            .requestMatchers("/hiring-manager/**").hasRole("HIRING_MANAGER")
            .requestMatchers("/employee/**").hasRole("EMPLOYEE")
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // For simplicity, not recommended for production
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                                .password(passwordEncoder().encode("admin123"))
                                .roles("ADMIN")
                                .build();

        UserDetails standard = User.withUsername("standard")
                                   .password(passwordEncoder().encode("standard123"))
                                   .roles("STANDARD")
                                   .build();

        UserDetails hiringManager = User.withUsername("hiringmanager")
                                        .password(passwordEncoder().encode("manager123"))
                                        .roles("HIRING_MANAGER")
                                        .build();

        UserDetails employee = User.withUsername("employee")
                                   .password(passwordEncoder().encode("employee123"))
                                   .roles("EMPLOYEE")
                                   .build();

        return new InMemoryUserDetailsManager(admin, standard, hiringManager, employee);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
