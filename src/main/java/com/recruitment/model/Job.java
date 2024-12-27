package com.recruitment.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private Integer vacancies;

    @ManyToMany(mappedBy = "appliedJobs")
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "job")
    private List<Application> applications;

    @OneToMany(mappedBy = "job")
    private List<Interview> interviews;
}
