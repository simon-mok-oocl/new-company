package com.example.newcompnay.Service;

import com.example.newcompnay.Entity.Company;
import com.example.newcompnay.Exception.NoSuchCompanyException;
import com.example.newcompnay.Repository.CompanyRepository;
import com.example.newcompnay.Repository.EmployeeRepository;
import com.example.newcompnay.dto.CompanyRequest;
import com.example.newcompnay.dto.CompanyResponse;
import com.example.newcompnay.dto.EmployeeResponse;
import com.example.newcompnay.mapper.CompanyMapper;
import com.example.newcompnay.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;
    private CompanyMapper companyMapper;
    private EmployeeMapper employeeMapper;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.companyMapper = new CompanyMapper();
        this.employeeMapper = new EmployeeMapper();
    }

    public List<CompanyResponse> getAll() {
        List<CompanyResponse> companies = new ArrayList<>();
        this.companyRepository.findAll().stream()
                .forEach(company -> companies.add(this.companyMapper.toDto(company , null)));

        return companies;
        //return this.companyRepository.findAll();
    }

    public CompanyResponse updateCompany(String id , CompanyRequest request) {
        Company useCompany = this.companyRepository.findById(id).orElseThrow(NoSuchCompanyException::new);

        if(request.getName() != null)
            useCompany.setName(request.getName());

        return this.companyMapper.toDto(this.companyRepository.save(useCompany) , null);
    }

    public CompanyResponse addCompany(CompanyRequest newCompany) {
        return this.companyMapper.toDto(this.companyRepository.save(this.companyMapper.toEntity(newCompany)) , null);
    }

    public void removeCompany(String id) {
        Company rip = this.companyRepository.findById(id).orElseThrow(NoSuchCompanyException::new);
        this.companyRepository.delete(rip);
    }

    public List<EmployeeResponse> getEmployeeByCompany(String id) {
        List<EmployeeResponse> employees = new ArrayList<>();
        this.employeeRepository.findAllByCompanyId(id).stream()
                .forEach(employee -> employees.add(this.employeeMapper.toDto(employee)));

        return employees;
    }
}
