package com.employwise.employwisespring.controller;

import com.employwise.employwisespring.entity.Employee;
import com.employwise.employwisespring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public UUID addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
    }

    @GetMapping("/nthLevelManager/{employeeId}/{level}")
    public ResponseEntity<Employee> getNthLevelManager(
            @PathVariable UUID employeeId,
            @PathVariable int level
    ) {
        Employee nthLevelManager = employeeService.getNthLevelManager(employeeId, level);
        if (nthLevelManager != null) {
            return ResponseEntity.ok(nthLevelManager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginatedAndSorted")
    public ResponseEntity<List<Employee>> getEmployeesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeName") String orderBy
    ) {
        List<Employee> employees = employeeService.getEmployeesWithPaginationAndSorting(page, size, orderBy);
        return ResponseEntity.ok(employees);
    }


}
