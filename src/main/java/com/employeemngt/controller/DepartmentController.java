package com.employeemngt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemngt.model.Department;
import com.employeemngt.service.DepartmentServiceImpl;
import com.employeemngt.service.EmployeeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/department")
@Api(description = "Department all Operation")
public class DepartmentController {

	private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	EmployeeServiceImpl employeeservice;

	@Autowired
	DepartmentServiceImpl departmentservice;

	@PostMapping("/addDepartment")
	@ApiOperation(value = "adding new department", notes = "adding new department", response = DepartmentController.class)
	public ResponseEntity<Department> create(@RequestBody Department department) {
		logger.info("adding employee data");
		return new ResponseEntity<Department>((departmentservice.addDepartment(department)), HttpStatus.CREATED);
	}

	@GetMapping("/getDepartmentById/{Department_id}")
	public ResponseEntity<Department> employeeById(@PathVariable("Department_id") String Department_id) {
		return new ResponseEntity<Department>(departmentservice.getDepartmentById(Department_id), HttpStatus.OK);
	}

	@GetMapping("/getAllDepartments")
	public ResponseEntity<List<Department>> getAllDepartments() {
		return new ResponseEntity<List<Department>>(departmentservice.getAllDepartment(), HttpStatus.OK);
	}

	@PutMapping("/updateDepartmentByID/{departmentID}")
	public ResponseEntity<Department> updateEmployeeByID(@RequestBody Department department,
			@PathVariable(value = "departmentID", required = false) String departmentID) {
		logger.info("update employee by ID : " + departmentID);
		department.setId(departmentID);
		return new ResponseEntity<Department>(departmentservice.editDepartmentDetails(department), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteDepartmentByID/{departmentId}")
	public String deleteEmployeeByID(@PathVariable(value = "departmentId", required = true) String departmentId) {
		return departmentservice.deleteDepartmentByid(departmentId);
	}

	@GetMapping("/Test")
	public String employeeById() {
		return "Test WithOut Security";
	}

}
