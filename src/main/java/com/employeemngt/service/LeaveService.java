package com.employeemngt.service;

import java.util.List;

import com.employeemngt.model.Leave;

public interface LeaveService {

	public Leave addLeaves(Leave leaves);

	public List<Leave> getLeaveByEmployeeId(String employee_id);

	public Leave editLeaveDetailsByEmployeeId(Leave leaves);

	public Leave applyLeavesByEmployeeId(Leave leaves);
	
}
