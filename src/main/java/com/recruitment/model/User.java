package com.recruitment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String territory;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id") // Foreign key to RoleProfile
    private RoleProfile role;

    @ManyToMany
    @JoinTable(
            name = "user_groups", // Join table
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();
//
//    @NotBlank(message = "Username is mandatory")
//    @Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters")
//    private String username;
//
//    @NotBlank(message = "Password is mandatory")
//    @Size(min = 6, message = "Password should be at least 6 characters")
//    private String password;
//
//    @NotBlank(message = "Role is mandatory")
//    private String role; // Admin, Recruiter, Candidate
//
////    @NotBlank(message = "Status is mandatory")
//    private String status;//// approved, pending, rejected
//
//    @NotBlank(message = "Email is mandatory")
//    @Email(message = "Email should be valid")
//    private String email;
//
//    @OneToMany(mappedBy = "user")  // This matches the "user" field in Application
//    private List<Application> applications;
//
//    @OneToMany(mappedBy = "user")
//    private List<Interview> interviews;
//
//    @OneToMany(mappedBy = "user")
//    private List<Offer> offers;

}
