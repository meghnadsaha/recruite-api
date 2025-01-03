package com.recruitment.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(columnDefinition = "TEXT")
    private String permissions; // JSON string for storing permissions
}
