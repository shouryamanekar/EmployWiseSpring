package com.employwise.employwisespring.repository;

import com.employwise.employwisespring.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByEmail(String email);

    List<Employee> findByReportsTo(UUID reportsTo);

    @Query("SELECT e FROM Employee e WHERE e.id = :employeeId")
    Employee findNthLevelManager(@Param("employeeId") UUID employeeId);

    @Query("SELECT e.email FROM Employee e WHERE e.id = (SELECT reportsTo FROM Employee WHERE id = :employeeId)")
    String findLevel1ManagerEmail(@Param("employeeId") UUID employeeId);

    @Query("SELECT e FROM Employee e ORDER BY :orderBy")
    List<Employee> findWithPaginationAndSorting(@Param("orderBy") String orderBy, Pageable pageable);

    Optional<Employee> findById(UUID employeeId); // Corrected method signature

}
