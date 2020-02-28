package com.employeemngt.service;

import java.util.List;

import com.employeemngt.model.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public Employee getEmployeeById(String employee_id);

	public Employee editEmployeeDetails(Employee employee);

	public void deleteEmployeeByid(String employee_id);

	public List<Employee> getAllEmployees();
}
