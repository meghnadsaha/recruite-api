package com.recruitment.dto;

import com.recruitment.model.Department;

public class DepartmentMapper {

    public static DepartmentDTO toDTO( Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setParentDepartmentId(
                department.getParentDepartment() != null ? department.getParentDepartment().getId() : null
        );
        dto.setDepartmentLeadId(
                department.getDepartmentLead() != null ? department.getDepartmentLead().getId()  : null
        );
        dto.setAttachmentPath(department.getAttachmentPath());
        return dto;
    }
}
