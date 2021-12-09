package com.example.newcompnay.Repository;

import com.example.newcompnay.Entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findAllByGender(String gender);
}
