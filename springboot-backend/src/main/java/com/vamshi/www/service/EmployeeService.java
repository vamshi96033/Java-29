package com.vamshi.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vamshi.www.exception.ResourceNotFoundException;
import com.vamshi.www.model.Employee;
import com.vamshi.www.repo.EmployeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	
	public Employee createEmployee(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	public Employee getEmployeeById(Long id)
	{
		return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found"));
	}

	public ResponseEntity<Employee> updateEmployee(Long id,Employee employee)
	{
		Employee emp=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Does not exist"));
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		Employee updateEmp=employeeRepository.save(emp);
		return ResponseEntity.ok(updateEmp);
	}
	
	public ResponseEntity<HttpStatus> deleteEmployee(Long id)
	{
		Employee emp=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID DOES NOT EXIST"));
		employeeRepository.delete(emp);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}