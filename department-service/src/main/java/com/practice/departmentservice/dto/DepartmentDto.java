package com.practice.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
	private Long id;
	private String  departmentName;
	private String  depatmentDescription;
	private String  departmentCode;
}
