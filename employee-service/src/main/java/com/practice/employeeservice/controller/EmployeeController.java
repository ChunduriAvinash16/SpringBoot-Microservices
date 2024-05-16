package com.practice.employeeservice.controller;

import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.dto.EmployeeResponse;
import com.practice.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	EmployeeService employeeService;

	@PostMapping
	ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
		return  new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(
			@PathVariable("id") Long employeeId) {
		EmployeeResponse EmployeeResponse = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(EmployeeResponse,HttpStatus.OK);
	}
}
