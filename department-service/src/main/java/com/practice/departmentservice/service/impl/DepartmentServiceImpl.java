package com.practice.departmentservice.service.impl;

import com.practice.departmentservice.dto.DepartmentDto;
import com.practice.departmentservice.entity.Department;
import com.practice.departmentservice.repository.DepartmentRepository;
import com.practice.departmentservice.service.DeparmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DeparmentService {

	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto deparmentDto) {
		//Converting DeparmentDto to Department JPA Entity
		Department department = new Department();
		BeanUtils.copyProperties(deparmentDto, department);
		Department savedDepartment =  departmentRepository.save(department);
		DepartmentDto savedDepartmentDto = new DepartmentDto();
		BeanUtils.copyProperties(savedDepartment, savedDepartmentDto);
		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDeparmentByCode(String departmentCode) {

		Department department = departmentRepository.findByDepartmentCode(departmentCode);
		DepartmentDto departmentDto = new DepartmentDto();
		BeanUtils.copyProperties(department, departmentDto);
		return departmentDto;
	}
}
