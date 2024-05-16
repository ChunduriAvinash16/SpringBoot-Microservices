package com.practice.employeeservice.service.impl;

import com.practice.employeeservice.dto.DepartmentDto;
import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.dto.EmployeeResponse;
import com.practice.employeeservice.entity.Employee;
import com.practice.employeeservice.exception.ResourceNotFoundException;
import com.practice.employeeservice.repository.EmployeeRepository;
import com.practice.employeeservice.service.ApiClient;
import com.practice.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

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

	@Override
	public EmployeeResponse getEmployeeById(Long employeeId) {
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
}
