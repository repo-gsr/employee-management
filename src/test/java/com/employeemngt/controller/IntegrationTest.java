package com.employeemngt.controller;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeemngt.EmployeeManagementApplication;
import com.employeemngt.model.Address;
import com.employeemngt.model.Department;
import com.employeemngt.model.Employee;
import com.employeemngt.model.RequestObject;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmployeeManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@Ignore
public class IntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private RequestObject requestObject;
	private Employee employee;
	private Address address;
	private Department department;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Before
	public void initialSetup() throws ParseException {
		requestObject = new RequestObject();

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
		address.setStreet("main road");
		address.setZipCode("560037");
		address.setAddType("Prasent");

		Address address2 = new Address();
		address2.setCity("banglore");
		address2.setCountry("india");
		address2.setStreet("main road");
		address2.setZipCode("560037");
		address2.setAddType("Prasent");

		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		addresses.add(address2);

		department = new Department();
		department.setName("account");
		department.setDescription("account related");

		employee.setAddress(addresses);
		employee.setDepartment(department);

		requestObject.setRequestdata(employee);

		ResponseEntity<Employee> response = restTemplate.postForEntity(getRootUrl() + "/employee/addEmployee",
				requestObject, Employee.class);
	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		// HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Employee[]> response = restTemplate.getForEntity(getRootUrl() + "/employee/getAllemployees",
				Employee[].class);
		System.out.println(response.getBody()[0].getFirstName() + " " + response.getBody()[0].getFirstName());
		assertNotNull(response.getBody());
	}

}
