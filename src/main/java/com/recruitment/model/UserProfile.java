package com.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.dto.Permissions;
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
    private String permissionsJson; // Store JSON string in the database

    @Transient
    private Permissions permissions; // Runtime object representation of permissions

    // Automatically convert Permissions object to JSON before persisting
    @PrePersist
    public void prePersist() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.permissionsJson = objectMapper.writeValueAsString(this.permissions);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error serializing permissions to JSON", e);
        }
    }

    // Automatically convert JSON to Permissions object after fetching from DB
    @PostLoad
    public void postLoad() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.permissions = objectMapper.readValue(this.permissionsJson, Permissions.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deserializing JSON to permissions", e);
        }
    }
}
