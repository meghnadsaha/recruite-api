package com.recruitment.service;

import com.recruitment.model.User;
import com.recruitment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    // Register user (for example, after admin approval)
// Register user (for example, after admin approval)
    public String registerUser ( User user ) {
        logger.info("Attempting to register user with username: {}" , user.getUsername());
        User currentAdmin = getCurrentAdmin();

        // Ensure only superadmin can create superadmin
//        if ("superadmin".equalsIgnoreCase(user.getRole())) {
//            if (currentAdmin == null || !"superadmin".equalsIgnoreCase(currentAdmin.getRole())) {
//                return "Only a superadmin can create a superadmin.";
//            }
//            user.setStatus("pending");  // Set the new superadmin status to pending
//        }
//        else if ("admin".equalsIgnoreCase(user.getRole())) {
//            if (currentAdmin == null || (!"superadmin".equalsIgnoreCase(currentAdmin.getRole()) && !"admin".equalsIgnoreCase(currentAdmin.getRole()))) {
//                return "Only a superadmin or admin can register another admin.";
//            }
//            user.setStatus("pending");  // Admin users are also pending until approved
//        }
//        else {
//            user.setStatus("pending");  // Default to pending for non-admin roles (e.g., candidates)
//        }

        if ("admin".equalsIgnoreCase(user.getRole())) {
            user.setStatus("approved");  // Default to pending for non-admin roles (e.g., candidates)
        } else {
            user.setStatus("pending");  // Default to pending for non-admin roles (e.g., candidates)
        }
        userRepository.save(user);
        logger.info("User registered successfully with username: {}" , user.getUsername());
        return "User registered successfully, awaiting approval.";
    }

    // Admin approve/reject user with role-based permission handling
// This method now only takes the userId of the user whose status is being updated
    public String approveRejectUser ( Long userId , String status ) {
        logger.info("Admin attempting to {} user with ID: {}" , status , userId);

        // Fetch the current logged-in admin from the security context
        User currentAdmin = getCurrentAdmin();

//        if (currentAdmin == null || !"superadmin".equalsIgnoreCase(currentAdmin.getRole())) {
//            return "Only superadmin can approve/reject users with admin or superadmin roles.";
//        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found";
        }

        // Ensuring the admin cannot approve/reject higher-level admins based on roles
//        if ("superadmin".equalsIgnoreCase(user.getRole())) {
//            return "Superadmin users cannot be approved/rejected by non-superadmins.";
//        }

        if (!"approved".equalsIgnoreCase(status) && !"rejected".equalsIgnoreCase(status)) {
            return "Invalid status. Use 'approved' or 'rejected'.";
        }


        if ("admin".equalsIgnoreCase(user.getRole())) {
            return "admin users cannot be approved/rejected by non-admin.";
        }
        user.setStatus(status);  // Update the status of the user
        userRepository.save(user);

        return "User status updated to '" + status + "' for user with ID: " + userId;
    }


    // Get pending users for admin review
    public List<User> getPendingUsers () {
        logger.info("Fetching all pending users for approval");
        // Retrieving users with 'pending' status
        List<User> pendingUsers = userRepository.findByStatus("pending");
        logger.info("Fetched {} pending users" , pendingUsers.size());
        return pendingUsers;
    }

    // Find user by username (for login purposes)
    public Optional<User> findUserByUsername ( String username ) {
        logger.info("Fetching user by username: {}" , username);
        Optional<User> user = userRepository.findByUsername(username);
        if (user != null) {
            logger.info("Found user with username: {}" , username);
        } else {
            logger.warn("User with username: {} not found" , username);
        }
        return user;
    }

    public String loginUser ( String username , String password ) {
        logger.info("Attempting login for username: {}" , username);
        Optional<User> user = findUserByUsername(username);

        // Check if the user is present
        if (user.isPresent()) {
            User foundUser = user.get();  // Get the User object from the Optional

            // Special case: Admin users are not subject to approval
            if ("admin".equalsIgnoreCase(foundUser.getRole())) {
                if (foundUser.getPassword().equals(password)) {
                    logger.info("Login successful for admin user: {}" , username);
                    return "Admin login successful!";
                } else {
                    logger.warn("Login failed for admin user: {} - Incorrect password" , username);
                    return "Incorrect password!";
                }
            }

            // Ensure the user is approved before logging in
            if ("approved".equalsIgnoreCase(foundUser.getStatus())) {
                if (foundUser.getPassword().equals(password)) {
                    logger.info("Login successful for username: {}" , username);
                    return "Login successful!";
                } else {
                    logger.warn("Login failed for username: {} - Incorrect password" , username);
                    return "Incorrect password!";
                }
            } else {
                logger.warn("Login failed for username: {} - User not approved" , username);
                return "Account not approved yet. Please wait for admin approval.";
            }
        }

        // If user is not present
        logger.warn("Login failed for username: {}" , username);
        return "User not found!";
    }


    public User findUserByEmail ( String email ) {
        return userRepository.findByEmail(email).orElse(null);  // Use the findByEmail method
    }

    // This method retrieves the current logged-in user (admin in this case)
    private User getCurrentAdmin () {
//         Get the current authentication object from the Spring Security context
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // Check if authentication is available and if the user is authenticated
//        if (authentication != null && authentication.isAuthenticated()) {
//            // Return the principal (logged-in user), assuming it is an instance of User
//            return (User) authentication.getPrincipal();  // Cast to your custom User class
//        }
        return null;  // If no authenticated user, return null
    }

}
