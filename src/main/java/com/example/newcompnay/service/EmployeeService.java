package com.example.newcompnay.service;

import com.example.newcompnay.entity.Employee;
import com.example.newcompnay.exception.NoSuchEmployeeException;
import com.example.newcompnay.repository.EmployeeRepository;
import com.example.newcompnay.dto.EmployeeRequest;
import com.example.newcompnay.dto.EmployeeResponse;
import com.example.newcompnay.mapper.EmployeeMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = new EmployeeMapper();
    }

    public List<EmployeeResponse> getAll() {
        List<EmployeeResponse> employees= new ArrayList<>();
        this.employeeRepository.findAll().stream()
                .forEach(employee -> employees.add(employeeMapper.toDto(employee)));

        return employees;
    }

    public EmployeeResponse getById(String id) {
        return employeeMapper.toDto(this.employeeRepository.findById(id).orElseThrow(NoSuchEmployeeException::new));
    }

    public EmployeeResponse updateEmployee(String id , EmployeeRequest patch)
    {
        Employee useEmployee = this.employeeRepository.findById(id).orElseThrow(NoSuchEmployeeException::new);

        if(patch.getAge() != null)
            useEmployee.setAge(patch.getAge());
        if(patch.getSalary() != null)
            useEmployee.setSalary(patch.getSalary());

        return this.employeeMapper.toDto(this.employeeRepository.save(useEmployee));
    }

    public List<EmployeeResponse> getEmployeeByGender(String gender) {
        List<EmployeeResponse> employees= new ArrayList<>();
        this.employeeRepository.findAllByGender(gender).stream()
                .forEach(employee -> employees.add(employeeMapper.toDto(employee)));

        return employees;
    }

    public EmployeeResponse addEmployee(EmployeeRequest newEmployee) {
        return this.employeeMapper.toDto(this.employeeRepository.save(employeeMapper.toEntity(newEmployee)));
    }

    public void removeEmployee(String id) {
        Employee rip = this.employeeRepository.findById(id).orElseThrow(NoSuchEmployeeException::new);
        this.employeeRepository.delete(rip);
    }

    public List<EmployeeResponse> getEmployeeInPage(Integer page, Integer pageSize) {
        List<EmployeeResponse> employees= new ArrayList<>();
        this.employeeRepository.findAll(PageRequest.of(page - 1 , pageSize)).stream()
                .forEach(employee -> employees.add(employeeMapper.toDto(employee)));

        return employees;
    }
}
