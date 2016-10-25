package org.test.service;

import java.util.List;

import org.test.entity.Employee;

public interface IEmployeeService {

	public Employee saveOrUpdate(Employee employee);
	
	public Employee getById(long id);
	
	public List<Employee> getAll();
}
