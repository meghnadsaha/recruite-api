package com.recruitment.dto;


import lombok.Data;

@Data
public class DepartmentDTO {
    /**
     * Summary of Options:
     *
     *     DTOs: Preferred for clean and flexible API responses.
     *     Force Initialization: Works but can lead to performance issues if overused.
     *     Jackson Hibernate Module: Simplifies handling of lazy-loaded fields.
     *     Ignore Lazy-Loaded Fields: Good for quick fixes but loses the field data in responses.
     *
     * For a scalable application, use DTOs to ensure clear separation of concerns and avoid exposing your internal entity structures directly.
     */
    private Long id;
    private String departmentName;
    private String parentDepartmentName; // Optional
    private String departmentLeadName;  // Optional
    private String attachmentPath;
}
