package com.recruitment.controller;

import com.recruitment.model.RoleProfile;
import com.recruitment.model.RoleProfileDTO;
import com.recruitment.service.RoleProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles-profiles")
@RequiredArgsConstructor
public class RoleProfileController {

    private final RoleProfileService roleProfileService;

    @PostMapping
    public ResponseEntity<RoleProfile> createRoleProfile( @RequestBody RoleProfileDTO roleProfileDTO) {
        return ResponseEntity.ok(roleProfileService.createRoleProfile(roleProfileDTO));
    }

    @GetMapping
    public ResponseEntity<List<RoleProfile>> getAllRoleProfile() {
        return ResponseEntity.ok(roleProfileService.getAllRoleProfile());
    }



}
