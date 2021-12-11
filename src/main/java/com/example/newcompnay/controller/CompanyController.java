package com.example.newcompnay.controller;

import com.example.newcompnay.entity.Company;
import com.example.newcompnay.service.CompanyService;
import com.example.newcompnay.dto.CompanyRequest;
import com.example.newcompnay.dto.CompanyResponse;
import com.example.newcompnay.dto.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompany() {
        return this.companyService.getAll();
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable String id, @RequestBody CompanyRequest request) {
        return this.companyService.updateCompany(id, request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody CompanyRequest newCompany) {
        return this.companyService.addCompany(newCompany);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable String id) {
        this.companyService.removeCompany(id);
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> getEmployeeByCompany(@PathVariable String id) {
        return this.companyService.getEmployeeByCompany(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> getCompanyByPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return this.companyService.getCompanyByPage(page, pageSize);
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable String id)
    {
        return this.companyService.getCompanyById(id);
    }
}
