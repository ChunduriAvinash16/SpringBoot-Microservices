package com.practice.departmentservice.service.impl;

import com.practice.departmentservice.dto.DepartmentDto;
import com.practice.departmentservice.entity.Department;
import com.practice.departmentservice.exception.ResourceNotFoundException;
import com.practice.departmentservice.repository.DepartmentRepository;
import com.practice.departmentservice.service.DeparmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DeparmentService {

	private DepartmentRepository departmentRepository;

	private ModelMapper modelMapper;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto deparmentDto) {
		//Converting DeparmentDto to Department JPA Entity
		Department department = modelMapper.map(deparmentDto,Department.class);
		Department savedDepartment =  departmentRepository.save(department);
		DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);
		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDeparmentByCode(String departmentCode) {

		Department department = departmentRepository.findByDepartmentCode(departmentCode)
				.orElseThrow(() -> new ResourceNotFoundException("Department Code Not Exists"));
		DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
		return departmentDto;
	}
}
