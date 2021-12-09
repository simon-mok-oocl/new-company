package com.example.newcompnay.mapper;

import com.example.newcompnay.Entity.Employee;
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
}
