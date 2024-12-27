package com.recruitment.model;

import jakarta.persistence.*;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")  // foreign key reference to User
    private User user;

    @OneToOne(mappedBy = "application")
    private Interview interview;

    @OneToOne(mappedBy = "application")
    private Offer offer;

    // Getters and setters...
}
