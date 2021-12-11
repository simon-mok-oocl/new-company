package com.example.newcompnay.Service;

import com.example.newcompnay.dto.CompanyRequest;
import com.example.newcompnay.entity.Company;
import com.example.newcompnay.repository.CompanyRepository;
import com.example.newcompnay.repository.EmployeeRepository;
import com.example.newcompnay.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
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
}