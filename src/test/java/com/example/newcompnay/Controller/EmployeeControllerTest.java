package com.example.newcompnay.Controller;

import com.example.newcompnay.entity.Employee;
import com.example.newcompnay.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeControllerTest
{

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    // default test data
    Employee c1e1 = new Employee("61b44db656be228d3166ebb2" , "c1e1" , 111 , "male" , 112 , "1");
    Employee c1e2 = new Employee("61b44db656be228d3166ebb3" , "c1e2" , 121 , "female" , 122 , "1");

    Employee c2e1 = new Employee("61b44db656be228d3166ebb4" , "c2e1" , 211 , "male" , 212 , "2");
    Employee c2e2 = new Employee("61b44db656be228d3166ebb5" , "c2e2" , 221 , "female" , 222 , "2");

    Employee c3e1 = new Employee("61b44db656be228d3166ebb6" , "c3e1" , 311 , "male" , 312 , "3");
    Employee c3e2 = new Employee("61b44db656be228d3166ebb7" , "c3e2" , 321 , "female" , 322 , "3");

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();

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
                .andExpect(jsonPath("$[0].id").value(c1e1.getId()))
                .andExpect(jsonPath("$[0].name").value("c1e1"))
                .andExpect(jsonPath("$[0].age").value(111))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].companyId").value("1"))
                .andExpect(jsonPath("$[1].id").value(c1e2.getId()))
                .andExpect(jsonPath("$[1].name").value("c1e2"))
                .andExpect(jsonPath("$[1].age").value(121))
                .andExpect(jsonPath("$[1].gender").value("female"))
                .andExpect(jsonPath("$[1].companyId").value("1"))
                .andExpect(jsonPath("$[2].id").value(c2e1.getId()))
                .andExpect(jsonPath("$[2].name").value("c2e1"))
                .andExpect(jsonPath("$[2].age").value(211))
                .andExpect(jsonPath("$[2].gender").value("male"))
                .andExpect(jsonPath("$[2].companyId").value("2"))
                .andExpect(jsonPath("$[3].id").value(c2e2.getId()))
                .andExpect(jsonPath("$[3].name").value("c2e2"))
                .andExpect(jsonPath("$[3].age").value(221))
                .andExpect(jsonPath("$[3].gender").value("female"))
                .andExpect(jsonPath("$[3].companyId").value("2"))
                .andExpect(jsonPath("$[4].id").value(c3e1.getId()))
                .andExpect(jsonPath("$[4].name").value("c3e1"))
                .andExpect(jsonPath("$[4].age").value(311))
                .andExpect(jsonPath("$[4].gender").value("male"))
                .andExpect(jsonPath("$[4].companyId").value("3"))
                .andExpect(jsonPath("$[5].id").value(c3e2.getId()))
                .andExpect(jsonPath("$[5].name").value("c3e2"))
                .andExpect(jsonPath("$[5].age").value(321))
                .andExpect(jsonPath("$[5].gender").value("female"))
                .andExpect(jsonPath("$[5].companyId").value("3"));

    }

    @Test
    public void should_return_employee_when_create_given_employee() throws Exception {
        // given
        String employee = "{\n" +
                "        \"name\": \"c4e1\",\n" +
                "        \"age\": 411,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 412\n" +
                "    }";


        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("c4e1"))
                .andExpect(jsonPath("$.age").value(411))
                .andExpect(jsonPath("$.gender").value("male"));

    }

    @Test
    public void should_get_correct_employee_when_get_employee_by_id_given_id() throws Exception {
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}" , c2e1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(c2e1.getId()))
                .andExpect(jsonPath("$.name").value("c2e1"))
                .andExpect(jsonPath("$.age").value(211))
                .andExpect(jsonPath("$.gender").value("male"));
    }

    @Test
    public void should_get_correct_employee_with_gender_when_get_employee_by_gender_given_gender() throws Exception {
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", "male"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(c1e1.getId()))
                .andExpect(jsonPath("$[0].name").value("c1e1"))
                .andExpect(jsonPath("$[0].age").value(111))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[1].id").value(c2e1.getId()))
                .andExpect(jsonPath("$[1].name").value("c2e1"))
                .andExpect(jsonPath("$[1].age").value(211))
                .andExpect(jsonPath("$[1].gender").value("male"))
                .andExpect(jsonPath("$[2].id").value(c3e1.getId()))
                .andExpect(jsonPath("$[2].name").value("c3e1"))
                .andExpect(jsonPath("$[2].age").value(311))
                .andExpect(jsonPath("$[2].gender").value("male"));

    }

    @Test
    public void should_get_correct_page_when_get_employee_by_page_given_page_and_pagesize() throws Exception {
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("page", "2").param("pageSize" , "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(c2e1.getId()))
                .andExpect(jsonPath("$[0].name").value("c2e1"))
                .andExpect(jsonPath("$[0].age").value(211))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].companyId").value("2"))
                .andExpect(jsonPath("$[1].id").value(c2e2.getId()))
                .andExpect(jsonPath("$[1].name").value("c2e2"))
                .andExpect(jsonPath("$[1].age").value(221))
                .andExpect(jsonPath("$[1].gender").value("female"))
                .andExpect(jsonPath("$[1].companyId").value("2"));;

    }

    @Test
    public void should_update_employee_when_updateEmployee_given_id_and_employee() throws Exception {
        String updateEmployee = "{\n" +
                "        \"age\": 50,\n" +
                "        \"salary\": 8000\n" +
                "    }";

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", c1e2.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateEmployee))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(c1e2.getId()))
                .andExpect(jsonPath("$.name").value("c1e2"))
                .andExpect(jsonPath("$.age").value(50))
                .andExpect(jsonPath("$.gender").value("female"));

    }

    @Test
    public void should_return_nothing_when_delete_given_id() throws Exception {
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}" , c3e1.getId()))
                .andExpect(status().isNoContent());

    }
}