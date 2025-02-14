package com.rest.assured.pojo;

public class Employee {

    private String name;
    private String email;
    private String gender;
    private String status;

    // Constructor
    public Employee(String name, String email, String gender, String status) {
    	
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    // Getters
  
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }
}
