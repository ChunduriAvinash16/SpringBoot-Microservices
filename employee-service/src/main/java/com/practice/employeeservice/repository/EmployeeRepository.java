package com.practice.employeeservice.repository;

import com.practice.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
//CRUD Operations
public interface EmployeeRepository extends JpaRepository<Employee,Long>
{

}
