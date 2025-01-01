package com.recruitment.service;


import com.recruitment.model.RoleProfile;
import com.recruitment.model.RoleProfileDTO;
import com.recruitment.repository.RoleProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleProfileService {

    private final RoleProfileRepository roleProfileRepository;

    public RoleProfile createRoleProfile( RoleProfileDTO roleProfileDTO) {
        RoleProfile roleProfile = new RoleProfile();
        roleProfile.setName(roleProfileDTO.getName());
        roleProfile.setDescription(roleProfileDTO.getDescription());
        return roleProfileRepository.save(roleProfile);
    }

    public List<RoleProfile> getAllRoleProfile() {
        return roleProfileRepository.findAll();
    }
}
