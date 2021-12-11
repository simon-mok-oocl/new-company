package com.example.newcompnay.Controller;

import com.example.newcompnay.entity.Employee;
import com.example.newcompnay.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeServiceTest
{

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();

        Employee c1e1 = new Employee("61b44db656be228d3166ebb2" , "c1e1" , 111 , "male" , 112 , "1");
        Employee c1e2 = new Employee("61b44db656be228d3166ebb3" , "c1e2" , 121 , "female" , 122 , "1");

        Employee c2e1 = new Employee("61b44db656be228d3166ebb4" , "c2e1" , 211 , "male" , 212 , "2");
        Employee c2e2 = new Employee("61b44db656be228d3166ebb5" , "c2e2" , 221 , "female" , 222 , "2");

        Employee c3e1 = new Employee("61b44db656be228d3166ebb6" , "c3e1" , 311 , "male" , 312 , "3");
        Employee c3e2 = new Employee("61b44db656be228d3166ebb7" , "c3e2" , 321 , "female" , 322 , "3");

        employeeRepository.save(c1e1);
        employeeRepository.save(c1e2);
        employeeRepository.save(c2e1);
        employeeRepository.save(c2e2);
        employeeRepository.save(c3e1);
        employeeRepository.save(c3e2);

    }

    @Test
    public void should_get_all_employees_when_getAllEmployees_given_employees() throws Exception {
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("c1e1"))
                .andExpect(jsonPath("$[0].age").value(111))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[1].id").isString())   // c1e2
                .andExpect(jsonPath("$[1].name").value("c1e2"))
                .andExpect(jsonPath("$[1].age").value(121))
                .andExpect(jsonPath("$[1].gender").value("female"))
                .andExpect(jsonPath("$[2].id").isString())   // c2e1
                .andExpect(jsonPath("$[2].name").value("c2e1"))
                .andExpect(jsonPath("$[2].age").value(211))
                .andExpect(jsonPath("$[2].gender").value("male"))
                .andExpect(jsonPath("$[3].id").isString())   // c2e1
                .andExpect(jsonPath("$[3].name").value("c2e2"))
                .andExpect(jsonPath("$[3].age").value(221))
                .andExpect(jsonPath("$[3].gender").value("female"))
                .andExpect(jsonPath("$[4].id").isString())   // c2e1
                .andExpect(jsonPath("$[4].name").value("c3e1"))
                .andExpect(jsonPath("$[4].age").value(311))
                .andExpect(jsonPath("$[4].gender").value("male"))
                .andExpect(jsonPath("$[5].id").isString())   // c2e1
                .andExpect(jsonPath("$[5].name").value("c3e2"))
                .andExpect(jsonPath("$[5].age").value(321))
                .andExpect(jsonPath("$[5].gender").value("female"));

    }
}