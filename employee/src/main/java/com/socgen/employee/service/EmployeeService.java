package com.socgen.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.socgen.employee.model.Employee;
import com.socgen.employee.repository.EmployeeRepository;

@Component
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public Employee registerEmployee(Employee e) {
		return repository.save(e);
	}
	
	public List<Employee> getAllBy(String sort) {
		return repository.findAll(Sort.by(Sort.Direction.ASC, sort));
	}
	

}
