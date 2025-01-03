package com.recruitment.controller;

import com.recruitment.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController ( AuthenticationManager authenticationManager , JwtUtil jwtUtil , UserDetailsService userDetailsService ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public Map<String, String> login( @RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = jwtUtil.generateToken(username, Map.of());
        return Map.of("token", token);
    }

}
