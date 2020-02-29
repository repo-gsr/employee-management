package com.employeemngt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemngt.model.Leave;
import com.employeemngt.service.LeaveServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/leave")
@Api(description = "Leave all Operation")
public class LeaveController {

	private final Logger logger = LoggerFactory.getLogger(LeaveController.class);

	@Autowired
	LeaveServiceImpl leaveService;

	@PostMapping("/addLeaves")
	@ApiOperation(value = "adding leaves to employee", notes = "adding leaves to employee")
	public ResponseEntity<Leave> create(@RequestBody Leave leave, String employee_id) {
		logger.info("adding leaves to eployee ");
		return new ResponseEntity<Leave>((leaveService.addLeaves(leave)), HttpStatus.CREATED);
	}

	@GetMapping("/getLeaveBalenceByEmployeeId/{Employee_id}")
	public ResponseEntity<List<Leave>> employeeById(@PathVariable("Employee_id") String Employee_id) {
		return new ResponseEntity<List<Leave>>(leaveService.getLeaveByEmployeeId(Employee_id), HttpStatus.OK);
	}

	@PutMapping("/editLeaveBalenceByEmployeeId/{Employee_id}")
	public ResponseEntity<Leave> editLeaveBalenceByEmployeeId(@RequestBody Leave leave,
			@PathVariable(value = "Employee_id", required = false) String Employee_id) {
		logger.info("update leave for employee ID : " + Employee_id);
		return new ResponseEntity<Leave>(leaveService.editLeaveDetailsByEmployeeId(leave), HttpStatus.CREATED);
	}

	@PostMapping("/applyLeavesByEmployeeId")
	@ApiOperation(value = "employee applying leaves", notes = "employee applying leaves", response = LeaveController.class)
	public ResponseEntity<Leave> applyLeavesByEmployeeId(@RequestBody Leave leave) {
		logger.info("employee applying leaves ");

		return new ResponseEntity<Leave>(leaveService.applyLeavesByEmployeeId(leave), HttpStatus.CREATED);
	}

}
