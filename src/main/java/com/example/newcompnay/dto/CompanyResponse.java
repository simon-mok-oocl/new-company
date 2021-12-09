package com.example.newcompnay.dto;

import java.util.List;

public class CompanyResponse {
    private String id;
    private String name;
    private List<EmployeeResponse> employees;

    public CompanyResponse() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
    }
}
