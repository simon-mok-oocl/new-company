package com.example.newcompnay.Controller;

import com.example.newcompnay.entity.Company;
import com.example.newcompnay.entity.Employee;
import com.example.newcompnay.repository.CompanyRepository;
import com.example.newcompnay.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // default test data
    Employee c1e1 = new Employee("61b44db656be228d3166ebb2" , "c1e1" , 111 , "male" , 112 , "61b44db656be228d3166ebb8");
    Employee c1e2 = new Employee("61b44db656be228d3166ebb3" , "c1e2" , 121 , "female" , 122 , "61b44db656be228d3166ebb8");

    Employee c2e1 = new Employee("61b44db656be228d3166ebb4" , "c2e1" , 211 , "male" , 212 , "61b44db656be228d3166ebb9");
    Employee c2e2 = new Employee("61b44db656be228d3166ebb5" , "c2e2" , 221 , "female" , 222 , "61b44db656be228d3166ebb9");

    Employee c3e1 = new Employee("61b44db656be228d3166ebb6" , "c3e1" , 311 , "male" , 312 , "61b44db656be228d3166ebba");
    Employee c3e2 = new Employee("61b44db656be228d3166ebb7" , "c3e2" , 321 , "female" , 322 , "61b44db656be228d3166ebba");

    Company c1 = new Company("61b44db656be228d3166ebb8" , "c1");
    Company c2 = new Company("61b44db656be228d3166ebb9" , "c2");
    Company c3 = new Company("61b44db656be228d3166ebba" , "c3");

    @BeforeEach
    void setUp() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();

        employeeRepository.save(c1e1);
        employeeRepository.save(c1e2);
        employeeRepository.save(c2e1);
        employeeRepository.save(c2e2);
        employeeRepository.save(c3e1);
        employeeRepository.save(c3e2);

        companyRepository.save(c1);
        companyRepository.save(c2);
        companyRepository.save(c3);
    }

    @Test
    public void should_get_all_companies_when_getAllCompanies_given_companies() throws Exception {
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(c1.getId()))
                .andExpect(jsonPath("$[0].name").value("c1"))
                .andExpect(jsonPath("$[1].id").value(c2.getId()))
                .andExpect(jsonPath("$[1].name").value("c2"))
                .andExpect(jsonPath("$[2].id").value(c3.getId()))
                .andExpect(jsonPath("$[2].name").value("c3"));

    }

    @Test
    public void should_return_correct_companies_when_getAllCompanies_given_id() throws Exception {
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}" , c2.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(c2.getId()))
                .andExpect(jsonPath("$.name").value("c2"))
                .andExpect(jsonPath("$.employees", hasSize(2)))
                .andExpect(jsonPath("$.employees[0].name").value("c2e1"))
                .andExpect(jsonPath("$.employees[0].age").value(211))
                .andExpect(jsonPath("$.employees[0].gender").value("male"))
                .andExpect(jsonPath("$.employees[1].name").value("c2e2"))
                .andExpect(jsonPath("$.employees[1].age").value(221))
                .andExpect(jsonPath("$.employees[1].gender").value("female"));

    }

    @Test
    public void should_get_all_employees_under_company_when_getAllEmployeesByCompanyId_given_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}/employees", c3.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("c3e1"))
                .andExpect(jsonPath("$[0].age").value(311))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[1].name").value("c3e2"))
                .andExpect(jsonPath("$[1].age").value(321))
                .andExpect(jsonPath("$[1].gender").value("female"));

    }

    @Test
    public void should_comapny_in_page_under_company_when_getAllCompanyByPage_given_page_pageSize() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/companies").param("page", "2")).param("pageSize" , "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(c3.getId()))
                .andExpect(jsonPath("$[0].name").value("c3"));

    }



}