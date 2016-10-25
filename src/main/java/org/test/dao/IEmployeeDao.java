package org.test.dao;

import java.util.List;

import org.test.entity.Employee;

public interface IEmployeeDao {

	public Employee saveOrUpdate(Employee employee);
	
	public Employee getById(long id);
	
	public List<Employee> getAll();
}
