package com.socgen.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.employee.model.Employee;
import com.socgen.employee.repository.EmployeeRepository;
import com.socgen.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "Employee" })
@RestController
@RequestMapping("/register/")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;

	@ApiOperation(value = "Retrieve Employees in ascending order of first name", notes = "Retrieving all employees from repository")
	@GetMapping("/employee")
	public List<Employee> getEmployees(
			@ApiParam(value = "Get all employess based on order", required = false) @RequestParam("sortDirection") String sortBy) {
		 return empService.getAllBy(sortBy);
   	}

	@ApiOperation(value = "Register Employee", notes = "User can register a particulate Employee")
	@PostMapping("/employee")
	public ResponseEntity registerEmployee(
			@ApiParam(value = "provide all details to register employee", required = false) @RequestBody Employee employee) {
		Employee e = empService.registerEmployee(employee);
		if(e == null)
		  return new ResponseEntity("No Content", HttpStatus.NO_CONTENT);
		else 
		return new ResponseEntity("Employee registered successfully", HttpStatus.CREATED);
		
	}
}
