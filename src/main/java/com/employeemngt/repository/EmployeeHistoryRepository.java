package com.employeemngt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employeemngt.model.Employee;
import com.employeemngt.model.EmployeeHistory;

@Repository
public interface EmployeeHistoryRepository extends MongoRepository<EmployeeHistory, String> {

	/*@Query("{'emp_id : ?0'}")
	Employee findEmployeeByName(String empName);*/

}
