package com.employeemngt.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.employeemngt.constant.EmployeeManagementConstants;
import com.employeemngt.model.Address;
import com.employeemngt.model.AddressHistory;
import com.employeemngt.model.Department;
import com.employeemngt.model.DepartmentHistory;
import com.employeemngt.model.Employee;
import com.employeemngt.model.EmployeeHistory;
import com.employeemngt.repository.AddressHistoryRepository;
import com.employeemngt.repository.AddressRepository;
import com.employeemngt.repository.DepartmentHistoryRepository;
import com.employeemngt.repository.DepartmentRepository;
import com.employeemngt.repository.EmployeeHistoryRepository;
import com.employeemngt.repository.EmployeeIdSequenceRepositoryImpl;
import com.employeemngt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeerepository;

	@Autowired
	AddressRepository addressrepository;

	@Autowired
	DepartmentRepository departmentrepository;

	@Autowired
	EmployeeHistoryRepository employeehistorypository;

	@Autowired
	AddressHistoryRepository addresshistoryrepository;

	@Autowired
	DepartmentHistoryRepository departmenthistoryrepository;

	@SuppressWarnings("unused")
	@Autowired
	private MongoOperations mongoOperation;

	@Autowired
	EmployeeIdSequenceRepositoryImpl employeeidSeq;

	@Override
	public Employee addEmployee(Employee employee) {
		employee.setId(
				Long.toString(employeeidSeq.getNextSequenceId(EmployeeManagementConstants.EMPLOYEE_ID_SEQUENCE_NAME)));
		logger.info("adding employee address");
		List<Address> address = addressrepository.save(employee.getAddress());
		employee.setAddress(address);

		logger.info("adding employee department");
		Department department = departmentrepository.save(employee.getDepartment());
		employee.setDepartment(department);

		return employeerepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(String employee_id) {
		return employeerepository.findOne(employee_id);
	}

	@Override
	public Employee editEmployeeDetails(Employee employee) {
		Employee emp = employeerepository.findOne(employee.getId());
		List<Address> address = employee.getAddress();
		Department department = employee.getDepartment();
		addressrepository.save(address);
		departmentrepository.save(department);
		return employeerepository.save(employee);
	}

	@Override
	public void deleteEmployeeByid(String employee_id) {
		logger.info("moving employee to history");
		Employee employee = employeerepository.findOne(employee_id);

		List<AddressHistory> addresshistory = new ArrayList<>();
		
		for (Address address : employee.getAddress()) {
			AddressHistory addrresshitry = new DozerBeanMapper().map(address, AddressHistory.class);
			addresshistory.add(addrresshitry);
		}

		DepartmentHistory departmenthistory = new DozerBeanMapper().map(employee.getDepartment(),
				DepartmentHistory.class);
		
		EmployeeHistory employeehistory = new DozerBeanMapper().map(employee, EmployeeHistory.class);
		
		movedataToHistory(employeehistory, addresshistory, departmenthistory);

		deleteEmployeeData(employee);
	}

	private void deleteEmployeeData(Employee employee) {
		addressrepository.delete(employee.getAddress());
		departmentrepository.delete(employee.getDepartment());
		employeerepository.delete(employee);
	}

	private void movedataToHistory(EmployeeHistory employee, List<AddressHistory> address,
			DepartmentHistory department) {
		addresshistoryrepository.save(address);
		departmenthistoryrepository.save(department);
		employeehistorypository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		logger.info("Fetching all employee");
		return employeerepository.findAll();
	}

}
