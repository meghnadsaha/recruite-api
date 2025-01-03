package com.recruitment.model;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "users") // Optional: specify table name explicitly
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    private String phone;
    private String address;
    private String territory;
}
