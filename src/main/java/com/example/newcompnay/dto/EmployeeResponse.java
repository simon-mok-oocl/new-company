package com.example.newcompnay.dto;

public class EmployeeResponse {
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private String companyId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
