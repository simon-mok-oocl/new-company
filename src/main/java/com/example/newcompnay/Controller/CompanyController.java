package com.example.newcompnay.Controller;

import com.example.newcompnay.Entity.Company;
import com.example.newcompnay.Service.CompanyService;
import com.example.newcompnay.dto.CompanyRequest;
import com.example.newcompnay.dto.CompanyResponse;
import com.example.newcompnay.dto.EmployeeResponse;
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
    public List<CompanyResponse> getAllCompany()
    {
        return this.companyService.getAll();
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable String id , @RequestBody CompanyRequest request)
    {
        return this.companyService.updateCompany(id , request);
    }

    @PostMapping
    public CompanyResponse addCompany(@RequestBody CompanyRequest newCompany)
    {
        return this.companyService.addCompany(newCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable String id)
    {
        this.companyService.removeCompany(id);
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> getEmployeeByCompany(@PathVariable String id)
    {
        return this.companyService.getEmployeeByCompany(id);
    }
}