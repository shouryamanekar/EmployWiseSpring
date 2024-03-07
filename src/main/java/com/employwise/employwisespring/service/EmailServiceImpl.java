package com.employwise.employwisespring.service;

import com.employwise.employwisespring.entity.Employee;
import com.employwise.employwisespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendNewEmployeeEmail(String managerEmail, Employee newEmployee) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("employwise.spring@gmail.com");
            message.setSubject("New Employee Added");
            message.setText("Dear Manager,\n\n"
                    + newEmployee.getEmployeeName() + " will now work under you. "
                    + "Mobile number is " + newEmployee.getPhoneNumber()
                    + " and email is " + newEmployee.getEmail() + ".");

            try {
                javaMailSender.send(message);
            } catch (MailException e) {
                // Handle email sending exception
                e.printStackTrace();
            }
        }
    }

