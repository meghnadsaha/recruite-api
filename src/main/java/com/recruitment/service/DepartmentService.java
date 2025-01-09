package com.recruitment.service;


import com.recruitment.model.Department;
import com.recruitment.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department) {
        if (department.getDepartmentName() == null || department.getDepartmentName().isBlank()) {
            throw new IllegalArgumentException("Department name is required");
        }
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                                   .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + id));
    }

    public Department updateDepartment(Long id, Department department) {
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setParentDepartment(department.getParentDepartment());
        existingDepartment.setDepartmentLead(department.getDepartmentLead());
        existingDepartment.setAttachmentPath(department.getAttachmentPath());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}
