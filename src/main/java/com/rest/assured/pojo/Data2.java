package com.rest.assured.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    public List<Employee> getEmployees() {
        return employees;
    }

}

