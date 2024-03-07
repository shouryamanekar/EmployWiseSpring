package com.employwise.employwisespring.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String employeeName;
    private String phoneNumber;
    private String email;

    @Column(name = "reports_to")
    private UUID reportsTo;
    private String profileImage;



    // Constructors, getters, and setters

    public Employee() {
        // Default constructor
    }

    public Employee(String employeeName, String phoneNumber, String email, UUID reportsTo, String profileImage) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.reportsTo = reportsTo;
        this.profileImage = profileImage;
    }

    // Getters and setters


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(UUID reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
