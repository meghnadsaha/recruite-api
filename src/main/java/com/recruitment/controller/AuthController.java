package com.recruitment.controller;

import com.recruitment.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

//    @Autowired
//    private UserService userService;


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

//    // Register new user (for candidates, consultancies, clients)
//    @Operation(summary = "Register a new user", description = "Register candidates, consultancies, and clients")
//    @ApiResponse(responseCode = "200", description = "User registration successful")
//    @ApiResponse(responseCode = "400", description = "Bad request")
//    @ApiResponse(responseCode = "500", description = "Internal server error")
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody @Valid User user, BindingResult result) {
//
//        // If validation errors are present
//        if (result.hasErrors()) {
//            StringBuilder errorMessage = new StringBuilder();
//            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
//            return ResponseEntity.status(400).body("Validation failed: " + errorMessage.toString());
//        }
//
//        // Proceed with user registration if no errors
//        try {
//            String response = userService.registerUser(user);
//            log.info("User registration successful for username: {}", user.getUsername());
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            log.error("Error during user registration for username: {}", user.getUsername(), e);
//            return ResponseEntity.status(500).body("Registration failed");
//        }
//    }
//
//    // User login
//    @Operation(summary = "Login a user", description = "Login users by providing username and password")
//    @ApiResponse(responseCode = "200", description = "Login successful")
//    @ApiResponse(responseCode = "401", description = "Invalid credentials")
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
//        String loginResponse = userService.loginUser(username, password);
//        if ("Login successful!".equalsIgnoreCase(loginResponse)) {
//            log.info("Login successful for username: {}", username);
//            return ResponseEntity.ok(loginResponse);
//        } else {
//            log.warn("Login failed for username: {}", username);
//            return ResponseEntity.status(401).body(loginResponse);
//        }
//    }
//
//    // Admin approve/reject user
//    @Operation(summary = "Approve or reject a user", description = "Approve or reject users after review")
//    @ApiResponse(responseCode = "200", description = "User status updated successfully")
//    @ApiResponse(responseCode = "400", description = "Invalid status provided")
//    @ApiResponse(responseCode = "500", description = "Error while updating user status")
//    @PutMapping("/admin/approve-user/{userId}")
//    public ResponseEntity<String> approveRejectUser(@PathVariable Long userId, @RequestParam String status) {
//        if (!"approved".equalsIgnoreCase(status) && !"rejected".equalsIgnoreCase(status)) {
//            return ResponseEntity.status(400).body("Invalid status. Use 'approved' or 'rejected'.");
//        }
//
//        // Ensure the user is an admin before proceeding with approval/rejection
//        String response = userService.approveRejectUser(userId, status);
//        return ResponseEntity.ok(response);
//    }
//
//    // Get pending users for approval (Admin only)
//    @Operation(summary = "Get all pending users for admin approval", description = "Retrieve all users who are pending approval by the admin")
//    @ApiResponse(responseCode = "200", description = "List of pending users")
//    @ApiResponse(responseCode = "500", description = "Error fetching pending users")
//    @GetMapping("/admin/pending-users")
//    public ResponseEntity<?> getPendingUsers() {
//        return ResponseEntity.ok(userService.getPendingUsers());
//    }

}
