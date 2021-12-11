package com.example.newcompnay.Service;

import com.example.newcompnay.dto.EmployeeRequest;
import com.example.newcompnay.dto.EmployeeResponse;
import com.example.newcompnay.entity.Company;
import com.example.newcompnay.entity.Employee;
import com.example.newcompnay.repository.EmployeeRepository;
import com.example.newcompnay.service.EmployeeService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void should_return_employee_list_when_getEmployeeList() {
        // given
        Employee employee1 = new Employee("1", "employee 1", 10, "male", 100, "1");
        Employee employee2 = new Employee("2", "employee 2", 10, "male", 100, "1");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        given(employeeRepository.findAll()).willReturn(employees);

        //when
        List<EmployeeResponse> actual = employeeService.getAll();

        //then
        EmployeeResponse employeeResponse1 = new EmployeeResponse();
        EmployeeResponse employeeResponse2 = new EmployeeResponse();
        BeanUtils.copyProperties(employee1 , employeeResponse1);
        BeanUtils.copyProperties(employee2 , employeeResponse2);

        List<EmployeeResponse> expected = new ArrayList<>();
        expected.add(employeeResponse1);
        expected.add(employeeResponse2);

        assertEquals(expected , actual);

    }

    @Test
    public void should_return_correct_employee_list_when_getEmployee_given_id()
    {
        // given
        Employee employee = new Employee("1" , "employee 1" , 10 , "male" , 100 , "1");
        given(employeeRepository.findById(any())).willReturn(java.util.Optional.of(employee));

        // when
        EmployeeResponse actual = employeeService.getById("1");

        // then
        EmployeeResponse expected = new EmployeeResponse();
        BeanUtils.copyProperties(employee , expected);
        assertEquals(expected , actual);

    }

    @Test
    public void should_return_correct_gender_list_when_getEmployeeByGender_given_gender()
    {
        // given
        Employee employee1 = new Employee("1" , "employee 1" , 10 , "male" , 100 , "1");
        List<Employee> male = new ArrayList<>();
        male.add(employee1);
        given(employeeRepository.findAllByGender(any())).willReturn(male);

        // when
        List<EmployeeResponse> actual = employeeService.getEmployeeByGender("male");

        // then
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee1 , employeeResponse);
        List<EmployeeResponse> expected = new ArrayList<>();
        expected.add(employeeResponse);

        assertEquals(expected , actual);

    }

    @Test
    void should_return_edited_employee_when_editEmployee_given_employee_patch()
    {
        // given
        Employee employee = new Employee("1" , "employee 1" , 10 , "male" , 100 ,"1" );
        given(employeeRepository.findById(any())).willReturn(java.util.Optional.of(employee));

        employee.setAge(11);
        employee.setSalary(200);
        given(employeeRepository.save(any())).willReturn(employee);

        // when
        EmployeeRequest employeePatch = new EmployeeRequest();
        employeePatch.setAge(11);
        employeePatch.setSalary(200);
        EmployeeResponse actual = employeeService.updateEmployee("1" , employeePatch);

        // then
        EmployeeResponse expected = new EmployeeResponse();
        BeanUtils.copyProperties(employee , expected);
        assertEquals(expected , actual);
    }

    @Test
    void should_return_deleted_employee_when_delete_employee_given_employee()
    {
        // given
        Employee employee = new Employee("1" , "employee 1" , 10 , "male" , 100,"1");
        given(employeeRepository.findById(any())).willReturn(java.util.Optional.of(employee));

        // when
        employeeService.removeEmployee("1");

        // then
        verify(employeeRepository).delete(employee);
    }

    @Test
    void should_return_new_employee_when_addEmployee_given_new_employee()
    {
        // given
        Employee employee = new Employee("1" , "employee 1" , 10 , "male" , 100,"1");
        EmployeeRequest request = new EmployeeRequest();
        BeanUtils.copyProperties(employee , request);

        given(employeeRepository.save(any())).willReturn(employee);

        // when
        EmployeeResponse actual = employeeService.addEmployee(request);

        // then
        EmployeeResponse expected = new EmployeeResponse();
        BeanUtils.copyProperties(employee , expected);
        assertEquals(expected , actual);
    }

    @Test
    public void should_return_employee_page_when_getCompanyByPage_given_page_pageSize()
    {
        // given
        Employee employee =  new Employee("1" , "employee 1" , 10 , "male" , 100,"1");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(employeeRepository.findAll(PageRequest.of(0 , 1))).willReturn(new PageImpl<>( employees, PageRequest.of(0,1) ,1));

        // when
        List<EmployeeResponse> actual = employeeService.getEmployeeInPage(1,1);

        // then
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee , employeeResponse);
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        employeeResponseList.add(employeeResponse);
        assertEquals(employeeResponseList , actual);

    }
}
