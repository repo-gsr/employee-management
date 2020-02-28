package com.employeemngt.repository;

public interface EmployeeIdSequenceRepository {

	long getNextSequenceId(String key);

}