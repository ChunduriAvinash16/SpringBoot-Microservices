package com.practice.departmentservice.controller;

import com.practice.departmentservice.dto.DepartmentDto;
import com.practice.departmentservice.service.DeparmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

	DeparmentService deparmentService;

	@PostMapping
	ResponseEntity<DepartmentDto> createDepartment(
			@RequestBody DepartmentDto departmentDto) {
		DepartmentDto savedDepartmentDto = deparmentService.saveDepartment(
				departmentDto);
		return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
	}

	@GetMapping("{code}")
	public ResponseEntity<DepartmentDto> getDepartmentByCode(
			@PathVariable("code") String departmentCode) {
		DepartmentDto departmentDto = deparmentService.getDeparmentByCode(departmentCode);
		return new ResponseEntity<>(departmentDto,HttpStatus.OK);
	}
}
