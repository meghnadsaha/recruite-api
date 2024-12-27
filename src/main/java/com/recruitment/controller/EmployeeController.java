package com.recruitment.controller;

import com.recruitment.model.Employee;
import com.recruitment.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        log.info("Received request to add employee: {}", employee);
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee added successfully: {}", savedEmployee);
        return savedEmployee;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        log.info("Received request to get employee with id: {}", id);
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            log.info("Employee found: {}", employee);
        } else {
            log.warn("Employee with id {} not found", id);
        }
        return employee;
    }
}
