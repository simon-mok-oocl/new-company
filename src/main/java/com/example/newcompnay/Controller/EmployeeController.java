package com.example.newcompnay.Controller;

import com.example.newcompnay.Entity.Employee;
import com.example.newcompnay.Service.EmployeeService;
import com.example.newcompnay.dto.EmployeeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployee()
    {
        return this.employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable String id)
    {
        return this.employeeService.getById(id);
    }
}
