package com.practice.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
	private Long id;
	private String  departmentName;
	private String  depatmentDescription;
	private String  departmentCode;
}
