//package com.recruitment.initializer;
//import com.recruitment.model.User;
//import com.recruitment.model.UserRole;
//import com.recruitment.repository.RoleRepository;
//import com.recruitment.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class AdminInitializer {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public void insertUser() {
//        try {
//            // Check if admin role exists
//            UserRole adminRole = roleRepository.findByName("Recruiter Admin")
//                                               .orElseGet(() -> {
//                                                   log.info("Recruiter Admin role not found. Creating...");
//                                                   UserRole role = new UserRole();
//                                                   role.setName("Recruiter Admin");
//                                                   role.setDescription("Top-level admin overseeing all recruitment activities");
//                                                   UserRole savedRole = roleRepository.save(role);
//                                                   log.info("Recruiter Admin role created.");
//                                                   return savedRole;
//                                               });
//
//            // Delay between role finding by name and user creation
//            log.info("Waiting for 2 seconds before creating the admin user...");
//            TimeUnit.SECONDS.sleep(2); // Delay of 2 seconds
//
//            // Check if admin user exists
//            if (!userRepository.existsByEmail("admin@example.com")) {
//                log.info("Admin user not found. Creating...");
//                User admin = User.builder()
//                                 .firstName("Recruiter Admin")
//                                 .lastName("User")
//                                 .email("admin@example.com")
//                                 .password(passwordEncoder.encode("admin123"))
//                                 .role(adminRole)
//                                 .enabled(true)
//                                 .address("851, Kalikapur Rd, Purbachal Kalitala, Kalikapur, Haltu, Kolkata, West Bengal 700099")
//                                 .phone("09836268960")
//                                 .territory("West Bengal")
//                                 .build();
//                userRepository.save(admin);
//                log.info("Admin user created with email: admin@example.com and password: admin123");
//            } else {
//                log.info("Admin user already exists.");
//            }
//        } catch (Exception e) {
//            log.error("Error initializing admin user: {}", e.getMessage(), e);
//        }
//    }
//}
