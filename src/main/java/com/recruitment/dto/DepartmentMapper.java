package com.recruitment.dto;

import com.recruitment.model.Department;

public class DepartmentMapper {

    public static DepartmentDTO toDTO( Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setParentDepartmentName(
                department.getParentDepartment() != null ? department.getParentDepartment().getDepartmentName() : null
        );
        dto.setDepartmentLeadName(
                department.getDepartmentLead() != null ? department.getDepartmentLead().getFirstName() + " " + department.getDepartmentLead().getLastName() : null
        );
        dto.setAttachmentPath(department.getAttachmentPath());
        return dto;
    }
}
