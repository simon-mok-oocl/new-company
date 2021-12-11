package com.example.newcompnay.mapper;

import com.example.newcompnay.entity.Company;
import com.example.newcompnay.dto.CompanyRequest;
import com.example.newcompnay.dto.CompanyResponse;
import com.example.newcompnay.dto.EmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMapper {
    public CompanyResponse toDto(Company company , List<EmployeeResponse> employees)
    {
        CompanyResponse response = new CompanyResponse();

        BeanUtils.copyProperties(company , response);
        response.setEmployees(employees);

        return response;
    }

    public Company toEntity(CompanyRequest request)
    {
        Company company = new Company();

        BeanUtils.copyProperties(request , company);

        return company;
    }
}
