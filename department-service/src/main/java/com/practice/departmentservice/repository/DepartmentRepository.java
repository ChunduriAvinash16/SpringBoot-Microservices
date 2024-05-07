package com.practice.departmentservice.repository;

import com.practice.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//CRUD Operation
public interface DepartmentRepository extends JpaRepository<Department,Long>
{
	Optional<Department> findByDepartmentCode(String departmentCode);
}
