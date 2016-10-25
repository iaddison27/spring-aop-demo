package org.test.controller.audit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.entity.Audit;
import org.test.service.AuditService;

@RunWith(MockitoJUnitRunner.class)
public class AuditControllerTest {
	
	private Clock clock = Clock.fixed(Instant.EPOCH, ZoneOffset.UTC);
	
	@Mock
	private AuditService auditService;
	
	@InjectMocks
	private AuditController auditController;
	
	@Test
	public void shouldReturnAllEmployees() {
		List<Audit> expected = new ArrayList<>();
		expected.add(new Audit("Math", "add", "2, 3", LocalDateTime.now(clock)));
		expected.add(new Audit("Math", "subtract", "10, 4", LocalDateTime.now(clock)));
		expected.add(new Audit("AdvancedMath", "add", "2, 3, 4", LocalDateTime.now(clock)));
		expected.add(new Audit("AdvancedMath", "multiply", "6, 1", LocalDateTime.now(clock)));
		
		when(auditService.getAll()).thenReturn(expected);
		
		assertEquals(expected, auditController.list());
		
		verify(auditService, times(1)).getAll();
	}
}
