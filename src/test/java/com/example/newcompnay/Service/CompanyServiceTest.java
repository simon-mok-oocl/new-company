package com.example.newcompnay.Service;

import com.example.newcompnay.dto.CompanyRequest;
import com.example.newcompnay.dto.CompanyResponse;
import com.example.newcompnay.dto.EmployeeRequest;
import com.example.newcompnay.dto.EmployeeResponse;
import com.example.newcompnay.entity.Company;
import com.example.newcompnay.entity.Employee;
import com.example.newcompnay.mapper.EmployeeMapper;
import com.example.newcompnay.repository.CompanyRepository;
import com.example.newcompnay.repository.EmployeeRepository;
import com.example.newcompnay.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    CompanyRepository companyRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    CompanyService companyService;


    @Test
    public void should_return_company_list_when_getCompanyList() {
        Company company = new Company("1", "spring");
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        given(companyRepository.findAll()).willReturn(companies);

        List<Company> actual = companyService.getAll();

        assertEquals(companies, actual);

    }

    @Test
    void should_update_company_when_updateCompany_given_company()
    {
        // given
        Company company = new Company("1" , "company1");
        given(companyRepository.findById(any())).willReturn(java.util.Optional.of(company));

        Company companyPatch = new Company("1" , "new company1");
        company.setName("new company1");
        given(companyRepository.save(any())).willReturn(company);

        // when
        CompanyRequest patch = new CompanyRequest();
        BeanUtils.copyProperties(company , patch);
        Company patchedCompany = companyService.updateCompany("1" , patch);


        // then
        verify(companyRepository).save(company);
        assertEquals(companyPatch , patchedCompany);
    }

//    @Test
//    public void should_return_company_when_getCompanyById_given_company()
//    {
//        // given
//        Company company = new Company("1", "spring");
//        given(companyRepository.findById(any())).willReturn(java.util.Optional.of(company));
//
//        Employee employee = new Employee("1" , "employee" , 1 , "male" , 2,  "1");
//        EmployeeResponse employeeResponse = new EmployeeResponse();
//        BeanUtils.copyProperties(employee , employeeResponse);
//        given(companyService.getEmployeeByCompany(any())).willReturn(Collections.singletonList(employeeResponse));
//
//        // when
//        CompanyResponse actual = companyService.getCompanyById("1");
//
//        // then
//
//        assertEquals("1" , actual.getId());
//        assertEquals("spring" , actual.getName());
//        assertEquals(employeeResponse , actual.getEmployees().get(0));
//
//    }

    @Test
    public void should_add_company_when_addCompany_given_company()
    {
        // given
        Company company = new Company("1", "spring");
        CompanyRequest companyRequest= new CompanyRequest();
        BeanUtils.copyProperties(company , companyRequest);
        given(companyRepository.save(any())).willReturn(company);

        Company actual = companyService.addCompany(companyRequest);

        assertEquals(company , actual);

    }

    @Test
    public void should_return_employee_when_getEmployeeByCompany_given_company_id()
    {
        // given
        Company company = new Company("1", "spring");
        Employee employee = new Employee("1" , "employee 1" , 1, "female" ,1 , "1");
        companyRepository.save(company);
        employeeRepository.save(employee);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(employeeRepository.findAllByCompanyId(any())).willReturn(employees);

        // when
        List<EmployeeResponse> actual = companyService.getEmployeeByCompany("1");

        // then
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee , employeeResponse);
        List<EmployeeResponse> expected = new ArrayList<>();
        expected.add(employeeResponse);
        assertEquals(expected , actual);
    }

    @Test
    public void should_return_company_page_when_getCompanyByPage_given_page_pageSize()
    {
        Company company = new Company("1", "spring");
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        given(companyRepository.findAll(PageRequest.of(0 , 1))).willReturn(new PageImpl<>(companies , PageRequest.of(0,1) ,1));

        List<Company> actual = companyService.getCompanyByPage(1,1);

        assertEquals(companies , actual);

    }

    @Test
    public void should_return_company_when_deleteCompany_given_company()
    {
        Company company = new Company("1", "spring");
        given(companyRepository.findById(any())).willReturn(java.util.Optional.of(company));

       companyService.removeCompany("1");

        verify(companyRepository).delete(company);

    }


}