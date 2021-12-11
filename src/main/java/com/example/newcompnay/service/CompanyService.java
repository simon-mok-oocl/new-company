package com.example.newcompnay.service;

import com.example.newcompnay.entity.Company;
import com.example.newcompnay.exception.NoSuchCompanyException;
import com.example.newcompnay.repository.CompanyRepository;
import com.example.newcompnay.repository.EmployeeRepository;
import com.example.newcompnay.dto.CompanyRequest;
import com.example.newcompnay.dto.CompanyResponse;
import com.example.newcompnay.dto.EmployeeResponse;
import com.example.newcompnay.mapper.CompanyMapper;
import com.example.newcompnay.mapper.EmployeeMapper;
import org.springframework.data.domain.PageRequest;
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

    public List<Company> getAll() {
        return this.companyRepository.findAll();
    }

    public Company updateCompany(String id , CompanyRequest request) {
        Company useCompany = this.companyRepository.findById(id).orElseThrow(NoSuchCompanyException::new);

        if(request.getName() != null)
            useCompany.setName(request.getName());

        return this.companyRepository.save(useCompany);
    }

    public Company addCompany(CompanyRequest newCompany) {
        return this.companyRepository.save(this.companyMapper.toEntity(newCompany));
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

    public List<CompanyResponse> getCompanyByPage(Integer page, Integer pageSize) {
        List<CompanyResponse> companies = new ArrayList<>();
        this.companyRepository.findAll(PageRequest.of(page -1 , pageSize)).stream()
                .forEach(company -> companies.add(companyMapper.toDto(company , null)));

        return companies;
    }

    public CompanyResponse getCompanyById(String id) {
        Company company = this.companyRepository.findById(id).orElseThrow(NoSuchCompanyException::new);
        List<EmployeeResponse> employee = this.getEmployeeByCompany(id);
        return this.companyMapper.toDto(company , employee);
    }
}
