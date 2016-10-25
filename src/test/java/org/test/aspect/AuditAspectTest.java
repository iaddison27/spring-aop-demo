package org.test.aspect;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.service.IAuditService;

@RunWith(MockitoJUnitRunner.class)
public class AuditAspectTest {

	@Mock
	private IAuditService auditService;
	
	private Clock clock = Clock.fixed(Instant.EPOCH, ZoneOffset.UTC);
	
	@InjectMocks
	private AuditAspect aspect = new AuditAspect(clock);
	
	@Test
	public void shouldCallAuditServiceWithCorrectParameters() {
		JoinPoint mock = mock(JoinPoint.class);
		Signature mockSignature = mock(Signature.class);		
		
		when(mock.getSignature()).thenReturn(mockSignature);
		when(mock.getTarget()).thenReturn("String");
		
		when(mockSignature.getName()).thenReturn("someMethod");
		Object[] args = new Object[] {"a", Integer.valueOf(2), "c"};
		when(mock.getArgs()).thenReturn(args);
		aspect.anyMethod(mock);
		
		verify(auditService).audit("class java.lang.String", "someMethod", args, LocalDateTime.now(clock));
	}
	
}
