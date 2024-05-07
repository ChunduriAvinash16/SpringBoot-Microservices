package com.practice.employeeservice.service.impl;

import com.practice.employeeservice.dto.EmployeeDto;
import com.practice.employeeservice.entity.Employee;
import com.practice.employeeservice.exception.ResourceNotFoundException;
import com.practice.employeeservice.repository.EmployeeRepository;
import com.practice.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeRepository employeeRepository;

	ModelMapper modelMapper;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = modelMapper.map(employeeDto,Employee.class);
		Employee saveEmployee = employeeRepository.save(employee);
		EmployeeDto savedEmployeeDto = modelMapper.map(saveEmployee,EmployeeDto.class);
		return  savedEmployeeDto;
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
			new ResourceNotFoundException("EmployeeId Not Found")
		);
		EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
		return employeeDto;
	}
}
