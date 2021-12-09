package com.example.newcompnay.Controller;

import com.example.newcompnay.Entity.Employee;
import com.example.newcompnay.Service.EmployeeService;
import com.example.newcompnay.dto.EmployeeRequest;
import com.example.newcompnay.dto.EmployeeResponse;
import com.example.newcompnay.mapper.EmployeeMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.employeeMapper = new EmployeeMapper();
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

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable String id , @RequestBody EmployeeRequest request)
    {
        return this.employeeService.updateEmployee(id , request);
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> getEmployeeByGender(@RequestParam String gender)
    {
        return this.employeeService.getEmployeeByGender(gender);
    }

    @PostMapping
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest newEmployee)
    {
        return this.employeeService.addEmployee(newEmployee);
    }
}
