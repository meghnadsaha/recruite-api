package com.recruitment.service;


import com.recruitment.model.UserRole;
import com.recruitment.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public UserRole createRole ( UserRole role ) {
        return roleRepository.save(role);
    }

    public List<UserRole> getAllRoles () {
        return roleRepository.findAll();
    }

    public UserRole updateRole ( Long id , UserRole updatedRole ) {
        UserRole role = roleRepository.findById(id)
                                      .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));
        role.setName(updatedRole.getName());
        role.setDescription(updatedRole.getDescription());
        role.setReportsTo(updatedRole.getReportsTo());
        role.setShareDataWithPeers(updatedRole.isShareDataWithPeers());
        return roleRepository.save(role);
    }

    public void deleteRole ( Long id ) {
        roleRepository.deleteById(id);
    }
}
