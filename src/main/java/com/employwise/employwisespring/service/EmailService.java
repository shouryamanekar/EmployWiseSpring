package com.employwise.employwisespring.service;

import com.employwise.employwisespring.entity.Employee;

public interface EmailService {

    void sendNewEmployeeEmail(String managerEmail, Employee newEmployee);
}
