package com.recruitment.repository;

import com.recruitment.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
}
