//package com.recruitment.model;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//public class Interview {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private LocalDateTime scheduledTime;
//    private String interviewType; // E.g., Phone, Video, In-person
//    private String status; // E.g., Scheduled, Completed, Canceled
//
//    @ManyToOne
//    @JoinColumn(name = "job_id")
//    private Job job;
//
//    @ManyToOne
//    @JoinColumn(name = "candidate_id")
//    private Candidate candidate;
//
//    @OneToOne
//    @JoinColumn(name = "application_id")
//    private Application application;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")  // foreign key to User entity
//    private User user;
//
//    // Getters and setters...
//}
