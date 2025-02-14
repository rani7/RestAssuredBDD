package com.rest.assured.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Data2 {
    @JsonProperty("organisation")
    private String organisationName;

    @JsonProperty("employee")
    private List<Employee> employees;

    // Constructor, getters, and setters
    public Data2(String organisationName, List<Employee> employees) {
        this.organisationName = organisationName;
        this.employees = employees;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

