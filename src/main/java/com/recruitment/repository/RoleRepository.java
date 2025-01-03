package com.recruitment.repository;


import com.recruitment.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
}
