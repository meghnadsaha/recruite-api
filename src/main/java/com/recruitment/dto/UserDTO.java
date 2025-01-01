package com.recruitment.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String territory;
    private Long roleId; // Foreign key to RoleProfile
    private Set<Long> groupIds; // Foreign keys to Group
    private String status;
}
