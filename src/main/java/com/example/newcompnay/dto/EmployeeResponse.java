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

    @Override
    public boolean equals(Object obj)
    {
        boolean sameId = ((EmployeeResponse)obj).getId().equals(this.id);
        boolean sameName = ((EmployeeResponse)obj).getName().equals(this.name);
        boolean sameAge = ((EmployeeResponse)obj).getAge().equals(this.age);
        boolean sameGender = ((EmployeeResponse)obj).getGender().equals(this.gender);
        boolean sameCompnayId = ((EmployeeResponse)obj).getCompanyId().equals(this.companyId);

        return sameId && sameName && sameAge && sameGender && sameCompnayId;
    }
}
