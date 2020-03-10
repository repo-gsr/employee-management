package com.employeemngt.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.model.Address;
import com.employeemngt.model.Department;
import com.employeemngt.model.Employee;
import com.employeemngt.service.EmployeeServiceImpl;

@RunWith(value = SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	public EmployeeController employeecontroller;

	private Employee employee;
	private Address address;
	private Department department;

	@Mock
	public EmployeeServiceImpl employeeservice;

	@Before
	public void initiaSetup() throws ParseException {
		employee = new Employee();
		employee.setId("12");
		employee.setFirstName("Vishnu");
		employee.setLastName("Vardan");
		employee.setGender("male");
		employee.setGmail("vishnu@gmail.com");
		employee.setPhoneNumber("9874563214");
		employee.setDateOfJoin(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"));
		employee.setDateOfBirth(new SimpleDateFormat("YYYY/mm/dd").parse("2019/05/02"));

		address = new Address();
		address.setCity("banglore");
		address.setCountry("india");

		List<Address> addresses = new ArrayList<>();
		addresses.add(address);

		department = new Department();
		department.setName("account");

		employee.setAddress(addresses);
		employee.setDepartment(department);

	}

	@Test
	public void createEmployeeTest() {
		when(employeeservice.addEmployee(anyObject())).thenReturn(employee);
		ResponseEntity<Employee> actualResult = employeecontroller.create(employee);
		Employee employeeResult = actualResult.getBody();
		assertThat("Vishnu", is(employeeResult.getFirstName()));
		assertThat("vishnu@gmail.com", is(employeeResult.getGmail()));
		assertThat("Vardan", is(employeeResult.getLastName()));
		assertThat("male", is(employeeResult.getGender()));
	}

	@Test
	public void employeeByIdTest() {
		when(employeeservice.getEmployeeById(anyObject())).thenReturn(employee);
		ResponseEntity<Employee> actualResult = employeecontroller.employeeById(employee.getId());
		Employee employeeResult = actualResult.getBody();
		assertThat("Vishnu", is(employeeResult.getFirstName()));
		assertThat("vishnu@gmail.com", is(employeeResult.getGmail()));
		assertThat("Vardan", is(employeeResult.getLastName()));
		assertThat("male", is(employeeResult.getGender()));
	}

	@Test
	public void getAllemployeesTest() {
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		when(employeeservice.getAllEmployees()).thenReturn(employees);
		ResponseEntity<List<Employee>> actualResult = employeecontroller.getAllemployees();
		List<Employee> employeeResult = actualResult.getBody();
		assertThat("Vishnu", is(employeeResult.get(0).getFirstName()));
		assertThat("vishnu@gmail.com", is(employeeResult.get(0).getGmail()));
		assertThat("Vardan", is(employeeResult.get(0).getLastName()));
		assertThat("male", is(employeeResult.get(0).getGender()));
	}

	@Test
	public void updateEmployeeByIDTest() {
		when(employeeservice.editEmployeeDetails(anyObject())).thenReturn(employee);
		ResponseEntity<Employee> actualResult = employeecontroller.updateEmployeeByID(employee, employee.getId());
		Employee employeeResult = actualResult.getBody();
		assertThat("Vishnu", is(employeeResult.getFirstName()));
		assertThat("vishnu@gmail.com", is(employeeResult.getGmail()));
		assertThat("Vardan", is(employeeResult.getLastName()));
		assertThat("male", is(employeeResult.getGender()));
	}

	@Test
	public void deleteEmployeeByIDTest() {
		doNothing().when(employeeservice).deleteEmployeeByid(anyString());
	}

	@Test
	public void addListOfEmployeesTest() throws IOException, ParseException {
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);

		MockMultipartFile csvfile = new MockMultipartFile("test.csv", "", "text/csv", "abc,pqr".getBytes());

		when(employeeservice.addListOfEmployees(anyObject())).thenReturn(employees);

		ResponseEntity<List<Employee>> employeesResult = employeecontroller.addListOfEmployees(csvfile);

		List<Employee> employeeResult = employeesResult.getBody();

		assertThat("Vishnu", is(employeeResult.get(0).getFirstName()));
		assertThat("vishnu@gmail.com", is(employeeResult.get(0).getGmail()));
		assertThat("Vardan", is(employeeResult.get(0).getLastName()));
		assertThat("male", is(employeeResult.get(0).getGender()));

	}
}
