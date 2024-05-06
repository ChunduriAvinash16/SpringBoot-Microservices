package com.practice.departmentservice.repository;

import com.practice.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
//CRUD Operation
public interface DepartmentRepository extends JpaRepository<Department,Long>
{
	Department findByDepartmentCode(String departmentCode);
}
