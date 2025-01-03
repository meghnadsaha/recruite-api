package com.recruitment.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "reports_to_id")
    private UserRole reportsTo;

    private boolean shareDataWithPeers;
}
