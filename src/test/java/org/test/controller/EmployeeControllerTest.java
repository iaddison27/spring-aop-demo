package org.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.entity.Employee;
import org.test.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Test
	public void shouldReturnAllEmployees() {
		List<Employee> expected = new ArrayList<>();
		expected.add(new Employee("Ian", 120.50, LocalDate.of(2015, 2, 1)));
		expected.add(new Employee("Robert", 99.00, LocalDate.of(2016, 7, 14)));
		expected.add(new Employee("Chris", 140.10, LocalDate.of(2015, 10, 12)));
		
		when(employeeService.getAll()).thenReturn(expected);
		
		assertEquals(expected, employeeController.list());
		
		verify(employeeService, times(1)).getAll();
	}
	
	@Test
	public void shouldReturnEmployeeWithTheSpecifiedId() {
		Employee expected = new Employee("Ian", 120.00,  LocalDate.of(2015, 2, 1));
		
		when(employeeService.getById(2L)).thenReturn(expected);
		
		assertEquals(expected, employeeController.getById(2L));
		
		verify(employeeService, times(1)).getById(2L);
	}
}
