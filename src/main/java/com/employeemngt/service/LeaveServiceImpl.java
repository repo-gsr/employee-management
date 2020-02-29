package com.employeemngt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemngt.model.Leave;
import com.employeemngt.repository.LeaveRepository;

@Service
public class LeaveServiceImpl implements LeaveService {

	private final Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);

	@Autowired
	LeaveRepository leaverepository;

	@Override
	public Leave addLeaves(Leave leaves) {
		return leaverepository.save(leaves);
	}

	@Override
	public List<Leave> getLeaveByEmployeeId(String employee_id) {
		return leaverepository.findByLeaveEmployeeId(employee_id);
	}

	@Override
	public Leave applyLeavesByEmployeeId(Leave leaves) {
		Leave leave = leaverepository.findByLeaveTypeAndLeaveEmployeeId(leaves.getLeaveType(),
				leaves.getLeaveEmployeeId());
		leaves.setLeaveId(leave.getLeaveId());
		/*
		 * leave.setDescription(leaves.getDescription());
		 * leave.setLeaveFrom(leaves.getLeaveFrom());
		 * leave.setLeaveTo(leaves.getLeaveTo());
		 * leave.setLeaveType(leaves.getLeaveType());
		 * leave.setLeavesBalence(leaves.getLeavesBalence());
		 * leave.setLeavesTotal(leaves.getLeavesBalence());
		 * leave.setLeavesUsed(leaves.getLeavesUsed());
		 */

		return leaverepository.save(leaves);
	}

	@Override
	public Leave editLeaveDetailsByEmployeeId(Leave leaves) {

		Leave leave = leaverepository.findByLeaveTypeAndLeaveEmployeeId(leaves.getLeaveType(),
				leaves.getLeaveEmployeeId());

		leaves.setLeaveId(leave.getLeaveId());

		return leaverepository.save(leaves);
	}

}
