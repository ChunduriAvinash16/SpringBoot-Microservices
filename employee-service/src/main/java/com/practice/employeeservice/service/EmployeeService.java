package com.practice.employeeservice.service;

import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.dto.EmployeeResponse;

public interface EmployeeService {
	EmployeeDto saveEmployee(EmployeeDto employeeDto);

	EmployeeResponse getEmployeeById(Long employeeId);
}
