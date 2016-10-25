package org.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.aspect.annotation.TimingLogging;
import org.test.dao.IEmployeeDao;
import org.test.entity.Employee;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@Override
	@Transactional
	public Employee saveOrUpdate(Employee employee) {
		return employeeDao.saveOrUpdate(employee);
	}
	
	@Override
	public Employee getById(long id) {
		return employeeDao.getById(id);
	}
	
	@Override
	@TimingLogging
	public List<Employee> getAll() {
		return employeeDao.getAll();
	}
}
