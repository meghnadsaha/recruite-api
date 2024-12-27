package com.recruitment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter @Setter
public class Employee {

    @Id
    private Long id;
    private String name;
    private String position;
    private String status;

}
