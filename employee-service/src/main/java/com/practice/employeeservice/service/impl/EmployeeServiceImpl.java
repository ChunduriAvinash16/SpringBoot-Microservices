package com.practice.employeeservice.service.impl;

import com.practice.employeeservice.dto.DepartmentDto;
import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.dto.EmployeeResponse;
import com.practice.employeeservice.entity.Employee;
import com.practice.employeeservice.exception.ResourceNotFoundException;
import com.practice.employeeservice.repository.EmployeeRepository;
import com.practice.employeeservice.service.ApiClient;
import com.practice.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	EmployeeRepository employeeRepository;

	ModelMapper modelMapper;

//	RestTemplate restTemplate;
//	WebClient webClient;
	ApiClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto,Employee.class);
		Employee saveEmployee = employeeRepository.save(employee);
		EmployeeDto savedEmployeeDto = modelMapper.map(saveEmployee,EmployeeDto.class);
		return  savedEmployeeDto;
	}

//	@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "departmentFallBack")
	@Retry(name = "${spring.application.name}" , fallbackMethod = "departmentFallBack")
	@Override
	public EmployeeResponse getEmployeeById(Long employeeId) {

		LOGGER.info("Get Employee By Id insideMethod");
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
			new ResourceNotFoundException("EmployeeId Not Found")
		);
//		ResponseEntity<DepartmentDto>  departmentDtoResponseEntity =restTemplate.getForEntity(
//				"http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
		// 	DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();

//		DepartmentDto departmentDto = webClient
//				.get()
//				.uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
//				.retrieve()
//				.bodyToMono(DepartmentDto.class)
//				.block();

		DepartmentDto departmentDto  = apiClient
				.getDepartmentByCode(employee.getDepartmentCode())
				.getBody();

		EmployeeResponse employeeResponse = new EmployeeResponse();
		EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
		employeeResponse.setEmployee(employeeDto);
		employeeResponse.setDepartment(departmentDto);
		return employeeResponse;
	}


	public EmployeeResponse departmentFallBack(Long employeeId,Exception exception) {

		LOGGER.info("Get Employee By Id fallbackMethod");
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
				new ResourceNotFoundException("EmployeeId Not Found")
		);

		DepartmentDto departmentDto  =  new DepartmentDto();
		departmentDto.setDepartmentCode("RD001");
		departmentDto.setDepartmentName("Research & Development");
		departmentDto.setDepatmentDescription("Research");

		EmployeeResponse employeeResponse = new EmployeeResponse();
		EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
		employeeResponse.setEmployee(employeeDto);
		employeeResponse.setDepartment(departmentDto);
		return employeeResponse;
	}
}
