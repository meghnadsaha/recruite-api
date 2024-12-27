package com.recruitment.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String offerDetails;
    private LocalDateTime offerDate;
    private LocalDateTime expiryDate;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "user_id")  // foreign key to User entity
    private User user;

    // Getters and setters...
}
