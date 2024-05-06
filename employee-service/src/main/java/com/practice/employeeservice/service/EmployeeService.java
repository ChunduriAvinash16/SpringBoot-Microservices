package com.practice.employeeservice.service;

import com.practice.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto saveEmployee(EmployeeDto employeeDto);

	EmployeeDto getEmployeeById(Long employeeId);
}
