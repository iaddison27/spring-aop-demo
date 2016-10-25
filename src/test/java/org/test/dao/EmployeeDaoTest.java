package org.test.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.test.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:repositoryTest-context.xml" })
public class EmployeeDaoTest {

	@Autowired
	protected EmployeeDao employeeDao;
	
	@Test
	@Transactional
    @Rollback(true)
	public final void shouldReturnAllEmployees() throws Exception {
		// Test data
		List<Employee> expected = new ArrayList<>();
		
		expected.add(createAndPersistEmployee("Ian", 120.50, LocalDate.of(2015, 2, 1)));
		expected.add(createAndPersistEmployee("Robert", 99.00, LocalDate.of(2016, 7, 14)));
		expected.add(createAndPersistEmployee("Chris", 140.10, LocalDate.of(2015, 10, 12)));
		
		assertEquals(expected, employeeDao.getAll());
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public final void shouldReturnEmployeeWithTheSpecifiedId() throws Exception {
		// Test data
		List<Employee> expected = new ArrayList<>();
		
		expected.add(createAndPersistEmployee("Ian", 120.50, LocalDate.of(2015, 2, 1)));
		expected.add(createAndPersistEmployee("Robert", 99.00, LocalDate.of(2016, 7, 14)));
		expected.add(createAndPersistEmployee("Chris", 140.10, LocalDate.of(2015, 10, 12)));
		
		assertEquals(expected.get(1), employeeDao.getById(expected.get(1).getId()));
	}

	private Employee createAndPersistEmployee(String name, double salary, LocalDate startDate) {
		Employee employee = new Employee(name, salary, startDate);
		return employeeDao.saveOrUpdate(employee);
	}
	
}
