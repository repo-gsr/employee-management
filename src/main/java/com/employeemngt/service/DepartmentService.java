package com.employeemngt.service;

import java.util.List;

import com.employeemngt.model.Department;

public interface DepartmentService {

	public Department addDepartment(Department department);

	public Department getDepartmentById(String department_id);

	public Department editDepartmentDetails(Department department);

	public String deleteDepartmentByid(String department_id);

	public List<Department> getAllDepartment();
}
