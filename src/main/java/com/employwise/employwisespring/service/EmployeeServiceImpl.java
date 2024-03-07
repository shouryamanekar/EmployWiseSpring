package com.employwise.employwisespring.service;



import com.employwise.employwisespring.entity.Employee;
import com.employwise.employwisespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public UUID addEmployee(Employee employee) {
        employee.setId(UUID.randomUUID());
        employeeRepository.save(employee);

        // Get the email of the level 1 manager
        //String managerEmail = getLevel1ManagerEmail(employee.getId());

        // Send an email notification
        //emailService.sendNewEmployeeEmail(managerEmail, employee);

        return employee.getId();
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void updateEmployee(UUID id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            // Update employee fields
            existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
            existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setReportsTo(updatedEmployee.getReportsTo());
            existingEmployee.setProfileImage(updatedEmployee.getProfileImage());
            employeeRepository.save(existingEmployee);
        }
    }

    public String getLevel1ManagerEmail(UUID  employeeId) {
        // Retrieve the employee by ID
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            // Get the reportsTo ID from the employee
            UUID reportsToId = optionalEmployee.get().getReportsTo();

            if (reportsToId != null) {
                // Retrieve the level 1 manager by ID
                Optional<Employee> optionalManager = employeeRepository.findById(reportsToId);

                if (optionalManager.isPresent()) {
                    // Return the email of the level 1 manager
                    return optionalManager.get().getEmail();
                }
            }
        }

        // If not found or not applicable, return null
        return null;
    }

    @Override
    public Employee getNthLevelManager(UUID employeeId, int level) {
        // Validate level parameter
        if (level <= 0) {
            throw new IllegalArgumentException("Level must be greater than zero");
        }

        // Initialize a set to track visited employees to avoid cyclic dependencies
        Set<UUID> visitedEmployees = new HashSet<>();
        UUID currentEmployeeId = employeeId;

        for (int i = 0; i < level; i++) {
            // Check if the employee is already visited
            if (visitedEmployees.contains(currentEmployeeId)) {
                return null; // Break the loop to avoid cyclic dependencies
            }

            // Retrieve the employee by ID
            Optional<Employee> optionalEmployee = employeeRepository.findById(currentEmployeeId);

            if (optionalEmployee.isPresent()) {
                // Get the reportsTo ID from the employee
                UUID reportsToId = optionalEmployee.get().getReportsTo();

                if (reportsToId == null) {
                    return null; // Employee is already at the top level
                }

                // Set the current employee ID for the next iteration
                visitedEmployees.add(currentEmployeeId);
                currentEmployeeId = reportsToId;

            } else {
                return null; // Employee not found
            }
        }

        // Retrieve the final nth level manager
        Optional<Employee> nthLevelManager = employeeRepository.findById(currentEmployeeId);
        return nthLevelManager.orElse(null);
    }



    @Override
    public List<Employee> getEmployeesWithPaginationAndSorting(int page, int size, String sortBy) {
        // Validate page and size parameters
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size parameters");
        }

        // Create a pageable object for pagination and sorting
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        // Retrieve the list of employees with pagination and sorting
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        // Log relevant information about the page
        System.out.println("Page Number: " + employeePage.getNumber());
        System.out.println("Total Pages: " + employeePage.getTotalPages());
        System.out.println("Total Elements: " + employeePage.getTotalElements());
        System.out.println("Sort: " + employeePage.getSort());


        // Return the content of the page
        return employeePage.getContent();
    }

}
