package com.employeemngt.controller;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employee")
@Api(description = "Employee all Operation")
public class Employee {

	@PostMapping("/create")
	@ApiOperation(value = "Create New Employee", notes = "Create New Employee", response = Employee.class)
	public String create() {
		return "Spring boot From Docker Image";
	}

	@GetMapping("/employeeById/{id}")
	public String employeeById(@PathParam("id") String id) {
		return "Spring boot From Docker Image";
	}

	@PutMapping("/updateEmployeeByID")
	public String updateEmployeeByID() {
		return "Spring boot From Docker Image";
	}

	@DeleteMapping("/deleteEmployeeByID")
	public String deleteEmployeeByID() {
		return "Spring boot From Docker Image";
	}
}
