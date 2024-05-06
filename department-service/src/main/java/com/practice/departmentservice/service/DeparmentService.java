package com.practice.departmentservice.service;

import com.practice.departmentservice.dto.DepartmentDto;

public interface DeparmentService {
	DepartmentDto saveDepartment(DepartmentDto deparmentDto);
	DepartmentDto getDeparmentByCode(String departmentCode);
}
