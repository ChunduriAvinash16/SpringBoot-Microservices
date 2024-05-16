package com.practice.employeeservice.service;

import com.practice.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080/",value = "DEPARTMENT-SERVICE")
// department-service is a service Id
//Automatically create a routes
@FeignClient(name = "department-service")
public interface ApiClient {
	@GetMapping("api/departments/{code}")
	public ResponseEntity<DepartmentDto> getDepartmentByCode(
			@PathVariable("code") String departmentCode);
}
