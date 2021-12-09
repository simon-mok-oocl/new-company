package com.example.newcompnay.Service;

import com.example.newcompnay.Entity.Employee;
import com.example.newcompnay.Exception.NoSuchEmployeeException;
import com.example.newcompnay.Repository.EmployeeRepository;
import com.example.newcompnay.dto.EmployeeResponse;
import com.example.newcompnay.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = new EmployeeMapper();
    }

    public List<EmployeeResponse> getAll() {
        List<EmployeeResponse> result = new ArrayList<>();

        this.employeeRepository.findAll()
                .stream()
                .forEach(employee -> result.add(employeeMapper.toDto(employee)));

        return result;
    }

    public EmployeeResponse getById(String id) {
        return employeeMapper.toDto(this.employeeRepository.findById(id).orElseThrow(NoSuchEmployeeException::new));
    }
}
