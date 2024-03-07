package com.employwise.employwisespring.service;

import com.employwise.employwisespring.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    UUID addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    void deleteEmployee(UUID employeeId);

    void updateEmployee(UUID employeeId, Employee updatedEmployee);

    Employee getNthLevelManager(UUID employeeId, int level);

    List<Employee> getEmployeesWithPaginationAndSorting(int page, int size, String orderBy);
}
