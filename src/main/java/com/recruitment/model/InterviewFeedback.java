package com.recruitment.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class InterviewFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feedback;
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;
}
