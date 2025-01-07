package com.recruitment.initializer;

import com.recruitment.dto.UserResponseDTO;
import com.recruitment.model.User;
import com.recruitment.model.UserProfile;
import com.recruitment.model.UserRole;
import com.recruitment.repository.ProfileRepository;
import com.recruitment.repository.RoleRepository;
import com.recruitment.repository.UserRepository;
import com.recruitment.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataLoader {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FileService fileService;


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

    private String token;

    public void setToken ( String token ) {
        this.token = token;
    }

    public void authenticateAndLoadData () throws Exception {

        insertAdminUser();
        authenticate();
        createRoles();
        createProfiles();
        createUsers();

        // Add more API calls here in order
        // ASCII Art Logo for "Data imported successfully."
        log.info("\n" +
                         "  ____        _        _         _                                      \n" +
                         " |  _ \\  __ _| |_ __ _| |__     (_) ___  ___ ___  ___  _ __             \n" +
                         " | | | |/ _` | __/ _` | '_ \\    | |/ _ \\/ __/ __|/ _ \\| '_ \\       \n" +
                         " | |_| | (_| | || (_| | |_) |   | |  __/\\__ \\__ \\ (_) | | | |         \n" +
                         " |____/ \\__,_|\\__\\__,_|_.__/    |_|\\___||___/___/\\___/|_| |_|     \n" +
                         "                                                                       \n" +
                         "                 Data imported successfully. ");
    }

    public void insertAdminUser () {
        try {
            // Check if admin role exists
            UserRole adminRole = roleRepository.findByName("Recruiter Admin")
                                               .orElseGet(() -> {
                                                   log.info("Recruiter Admin role not found. Creating...");
                                                   UserRole role = new UserRole();
                                                   role.setName("Recruiter Admin");
                                                   role.setDescription(
                                                           "Top-level admin overseeing all recruitment activities");
                                                   UserRole savedRole = roleRepository.save(role);
                                                   log.info("Recruiter Admin role created.");
                                                   return savedRole;
                                               });

            UserProfile userProfile = profileRepository.findByName("Administrator")
                                                       .orElseGet(() -> {
                                                           log.info("Administrator  profile not found. Creating...");

                                                           UserProfile profile = new UserProfile();
                                                           profile.setName("Administrator");
                                                           profile.setDescription("This profile will have all the permissions");
                                                           profile.setPermissionsJson("{\"modulePermissions\":[{\"entity\":\"Home\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Job Openings\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Applications\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Candidates\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Referrals\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Interviews\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Departments\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Analytics\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Metrics\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Dashboards\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Reports\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Campaigns\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Assessments\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"To-Dos\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Vendors\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Notes\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Recruiter Inbox\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"My Actions\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Emails\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Documents\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Submissions\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true},{\"entity\":\"Offers\",\"tabVisible\":true,\"view\":true,\"create\":true,\"edit\":true,\"delete\":true}],\"socialPermissions\":{\"socialAdmin\":true,\"managePosts\":true,\"viewAnalytics\":true}}");

                                                           UserProfile savedProfile = profileRepository.save(profile);
                                                           log.info("Administrator  profile created.");
                                                           return savedProfile;
                                                       });

            // Delay between role finding by name and user creation
            log.info("Waiting for 2 seconds before creating the admin user...");
            TimeUnit.SECONDS.sleep(2); // Delay of 2 seconds

            // Check if admin user exists
            if (!userRepository.existsByEmail("admin@example.com")) {
                log.info("Admin user not found. Creating...");
                User admin = User.builder()
                                 .firstName("Recruiter Admin")
                                 .lastName("User")
                                 .email("admin@example.com")
                                 .password(passwordEncoder.encode("admin123"))
                                 .role(adminRole)
                                 .profile(userProfile)
                                 .enabled(true)
                                 .address(
                                         "851, Kalikapur Rd, Purbachal Kalitala, Kalikapur, Haltu, Kolkata, West Bengal 700099")
                                 .phone("09836268960")
                                 .territory("West Bengal")
                                 .build();
                userRepository.save(admin);
                log.info("Admin user created with email: admin@example.com and password: admin123");
            } else {
                log.info("Admin user already exists.");
            }
        } catch (Exception e) {
            log.error("Error initializing admin user: {}" , e.getMessage() , e);
        }
    }

    private void authenticate () {
        String url = "http://localhost:8080/api/auth/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare the request payload
        Map<String, String> requestBody = Map.of(
                "username" , "admin@example.com" ,
                "password" , "admin123"
        );

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody , headers);

        // Exchange with RestTemplate
        ResponseEntity<UserResponseDTO> response = restTemplate.exchange(
                url ,
                HttpMethod.POST ,
                entity ,
                new ParameterizedTypeReference<>() {
                }
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            UserResponseDTO responseBody = response.getBody();
            if (responseBody != null && responseBody.getToken() != null) {
                token = responseBody.getToken();
                // Optionally log user details
                log.info("Authentication successful for user: {} {}" , responseBody.getFirstName() ,
                         responseBody.getLastName());
            } else {
                throw new RuntimeException("Token not found in response");
            }
        } else {
            throw new RuntimeException("Authentication failed with status: " + response.getStatusCode());
        }
    }


    /**
     * Create the Bottom-Level Role (Employee)
     */
    public void createRoles () throws Exception {
        String url = "http://localhost:8080/api/roles";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String[] cases = {"recruiter-role-definition.json" , "hiring-manager-role-definition.json" , "employee-role-definition.json"};

        for (String fileName : cases) {
            log.info("Starting creation process for role file: {}" , fileName);

            String body = fileService.getFileContent(fileName); // Load file content
            HttpEntity<String> entity = new HttpEntity<>(body , headers);

            try {
                log.info("Sending POST request to {} for file: {}" , url , fileName);
                ResponseEntity<UserRole> response = restTemplate.exchange(url , HttpMethod.POST , entity ,
                                                                          UserRole.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    log.info("Successfully created role: {}" , response.getBody());
                } else {
                    log.error("Failed to create role for file: {}. Response status: {}" , fileName ,
                              response.getStatusCode());
                }
            } catch (Exception e) {
                log.error("An error occurred while creating role for file: {}. Error: {}" , fileName , e.getMessage());
            }

            log.info("Delaying for 2 seconds before processing the next file...");
            TimeUnit.SECONDS.sleep(2); // 2 seconds delay
        }

        log.info("Finished processing all role files.");
    }

    /**
     * Create Profile for Administrator
     */
    private void createProfiles () throws Exception {
        String url = "http://localhost:8080/api/profiles";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String cases[] = {"administrator-profile-definition.json" , "standard-profile-definition.json" ,
                "hiring-manager-profile-definition.json" , "employee-profile-definition.json"};

        for (String aCase : cases) {
            log.info("Processing file: {}" , aCase);

            String body = fileService.getFileContent(aCase); // Replace with the desired file name
            HttpEntity<String> entity = new HttpEntity<>(body , headers);

            try {
                log.info("Sending request to create profile using file: {}" , aCase);
                ResponseEntity<UserProfile> response = restTemplate.exchange(url , HttpMethod.POST , entity ,
                                                                             UserProfile.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    log.info("Profile successfully created: {}" , response.getBody());
                } else {
                    log.error("Failed to create profile. Status code: {}" , response.getStatusCode());
                    throw new RuntimeException("Failed to create profile");
                }
            } catch (Exception e) {
                log.error("Error occurred while creating profile from file {}: {}" , aCase , e.getMessage() , e);
                throw e;
            }

            // Add a 2-second delay
            log.info("Adding a delay of 2 seconds before processing the next file...");
            Thread.sleep(2000);
        }

        log.info("Completed processing all files for profiles.");
    }


    /**
     * Create User
     */
    private void createUsers () throws Exception {
        String url = "http://localhost:8080/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String[] cases = {
//                "admin.json",
                "admin-user.json" , "user1.json" , "user2.json" , "user3.json" , "user4.json" , "user5.json" ,
                "user6.json" , "user7.json" , "user8.json" , "user9.json" , "user10.json" , "user11.json" , "user12.json"};

        for (String fileName : cases) {
            log.info("Starting processing for file: {}" , fileName);

            try {
                // Get file content and create request
                String body = fileService.getFileContent(fileName);
                HttpEntity<String> entity = new HttpEntity<>(body , headers);

                log.info("Sending POST request to {} for file: {}" , url , fileName);
                ResponseEntity<User> response = restTemplate.exchange(url , HttpMethod.POST , entity , User.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    log.info("Successfully created user: {}" , response.getBody());
                } else {
                    log.error("Failed to create user from file {}. Status code: {}" , fileName ,
                              response.getStatusCode());
                }
            } catch (Exception e) {
                log.error("An error occurred while processing file {}: {}" , fileName , e.getMessage() , e);
            }

            // Add a 2-second delay
            log.info("Adding a delay of 2 seconds before processing the next file...");
            Thread.sleep(2000);
        }

        log.info("All user files have been processed.");
    }


}
