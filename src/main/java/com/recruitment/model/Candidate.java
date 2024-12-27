package com.recruitment.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String resumeUrl;

    @ManyToMany
    @JoinTable(name = "candidate_job",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private List<Job> appliedJobs;

    @OneToMany(mappedBy = "candidate")
    private List<Interview> interviews;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;

    @OneToMany(mappedBy = "candidate")
    private List<Offer> offers;
}
