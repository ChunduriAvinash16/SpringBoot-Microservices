package com.practice.departmentservice.mapper;

import com.practice.departmentservice.dto.DepartmentDto;
import com.practice.departmentservice.entity.Department;

public class DepartmentMapper {
    public static Department mapToDepartment(DepartmentDto departmentDto) {
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepatmentDescription(),
                departmentDto.getDepartmentCode()
        );
        return department;
    }

    public static DepartmentDto mapToDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepatmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
}
