package com.example.newcompnay.controller;

import com.example.newcompnay.service.EmployeeService;
import com.example.newcompnay.dto.EmployeeRequest;
import com.example.newcompnay.dto.EmployeeResponse;
import com.example.newcompnay.mapper.EmployeeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest newEmployee)
    {
        return this.employeeService.addEmployee(newEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable String id)
    {
        this.employeeService.removeEmployee(id);
    }

    @GetMapping(params = {"page" , "pageSize"})
    public List<EmployeeResponse> getEmployeeByPage(@RequestParam Integer page , @RequestParam Integer pageSize)
    {
        return this.employeeService.getEmployeeInPage(page , pageSize);
    }
}
