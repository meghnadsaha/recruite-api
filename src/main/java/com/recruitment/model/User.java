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
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
       // implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;

    @NotBlank(message = "Role is mandatory")
    private String role; // Admin, Recruiter, Candidate

//    @NotBlank(message = "Status is mandatory")
    private String status;//// approved, pending, rejected

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(mappedBy = "user")  // This matches the "user" field in Application
    private List<Application> applications;

    @OneToMany(mappedBy = "user")
    private List<Interview> interviews;

    @OneToMany(mappedBy = "user")
    private List<Offer> offers;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities () {
//        // In this example, we map the role to a GrantedAuthority (Spring Security's role)
//        return AuthorityUtils.createAuthorityList("ROLE_" + this.role);
//
//    }
//
//    @Override
//    public boolean isAccountNonExpired () {
////        return UserDetails.super.isAccountNonExpired();
//        return true; // For simplicity, assume account is not expired
//    }
//
//    @Override
//    public boolean isAccountNonLocked () {
////        return UserDetails.super.isAccountNonLocked();
//        return true; // For simplicity, assume account is not expired
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired () {
////        return UserDetails.super.isCredentialsNonExpired();
//        return true; // For simplicity, assume account is not expired
//    }
//
//    @Override
//    public boolean isEnabled () {
////        return UserDetails.super.isEnabled();
//        return true; // For simplicity, assume account is not expired
//    }

    // Getters and setters...
}
