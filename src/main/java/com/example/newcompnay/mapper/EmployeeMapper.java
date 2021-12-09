package com.example.newcompnay.mapper;

import com.example.newcompnay.Entity.Employee;
import com.example.newcompnay.dto.EmployeeRequest;
import com.example.newcompnay.dto.EmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeResponse toDto(Employee employee)
    {
        EmployeeResponse response = new EmployeeResponse();

        BeanUtils.copyProperties(employee , response);

        return response;
    }

    public Employee toEntity(EmployeeRequest request)
    {
//        private String id;
//        private String name;
//        private Integer age;
//        private String gender;
//        private Integer salary;
//        private String companyId;

        Employee employee = new Employee();

        BeanUtils.copyProperties(request , employee);

//        employee.setId(null);
//        employee.setName(request.getName());
//        employee.setAge(request.getAge());
//        employee.setGender(request.getGender());
//        employee.setSalary(request.getSalary());
//        employee.setCompanyId(request.getCompanyId());

        return employee;
    }

}
