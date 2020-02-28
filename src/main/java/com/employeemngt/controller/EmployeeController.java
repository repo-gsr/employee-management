package com.employeemngt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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

import com.employeemngt.model.Employee;
import com.employeemngt.service.EmployeeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employee")
@Api(description = "Employee all Operation")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeServiceImpl employeeservice;

	@PostMapping("/addEmployee")
	@ApiOperation(value = "adding new employee", notes = "adding new employee", response = EmployeeController.class)
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {
		logger.info("adding employee data");
		return new ResponseEntity<Employee>(employeeservice.addEmployee(employee), HttpStatus.CREATED);
	}

	@GetMapping("/getemployeeById/{employee_id}")
	public ResponseEntity<Employee> employeeById(@PathVariable("employee_id") String employee_id) {
		return new ResponseEntity<Employee>(employeeservice.getEmployeeById(employee_id), HttpStatus.OK);
	}

	@GetMapping("/getAllemployees")
	public ResponseEntity<List<Employee>> getAllemployees() {
		return new ResponseEntity<List<Employee>>(employeeservice.getAllEmployees(), HttpStatus.OK);
	}

	@PutMapping("/updateEmployeeByID/{employeeId}")
	public ResponseEntity<Employee> updateEmployeeByID(@RequestBody Employee employee,
			@PathVariable(value = "employeeId", required = false) String employeeId) {
		logger.info("update employee by ID : " + employeeId);
		employee.setId(employeeId);
		return new ResponseEntity<Employee>(employeeservice.editEmployeeDetails(employee), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteEmployeeByID/{employeeId}")
	public void deleteEmployeeByID(@PathVariable(value = "employeeId",required = true) String employeeId) {
		employeeservice.deleteEmployeeByid(employeeId);
	}

	
	@GetMapping("/Test")
	public String employeeById() {
		return "Test WithOut Security";
	}

}
