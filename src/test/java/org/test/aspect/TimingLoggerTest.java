package org.test.aspect;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.service.IAuditService;

@RunWith(MockitoJUnitRunner.class)
public class TimingLoggerTest {

	@Mock
	private IAuditService auditService;
	
	private Clock clock = Clock.fixed(Instant.EPOCH, ZoneOffset.UTC);
	
	// Required so that we can capture arguments sent to the Logger
	@Mock
	private Appender mockAppender;
	@Captor
	private ArgumentCaptor<LoggingEvent> captorLoggingEvent;
	
	@InjectMocks
	private TimingLoggerAspect aspect = new TimingLoggerAspect(clock);
	
	private void setupLogger(Level logLevel) {
		// Setup the logger
		Logger root = Logger.getLogger(TimingLoggerAspect.class);
		root.addAppender(mockAppender);
		root.setLevel(logLevel);
	}
	
	@Test
	public void shouldCallLoggerWhenLogLevelIsInfo() throws Throwable {
		// Setup the Logger for this test
		setupLogger(Level.INFO);
		
		ProceedingJoinPoint mock = mock(ProceedingJoinPoint.class);
		MethodSignature mockSignature = mock(MethodSignature.class);
		// Need a real java.lang.reflect.Method
		Method testMethod = this.getClass().getMethod("test");
		
		when(mock.getSignature()).thenReturn(mockSignature);
		when(mockSignature.getMethod()).thenReturn(testMethod);
		when(mock.getTarget()).thenReturn("String");
		
		aspect.log(mock, null);
		
		// Ensure the Logger was called twice
		verify(mockAppender, times(2)).doAppend(captorLoggingEvent.capture());
		
		// Ensure values passed to the Logger were correct
		List<LoggingEvent> actualLoggingEvents = captorLoggingEvent.getAllValues();
		assertEquals("[String.test][Start:1970-01-01T00:00:00]", actualLoggingEvents.get(0).getMessage());
		assertEquals("[String.test][Finish:1970-01-01T00:00:00]", actualLoggingEvents.get(1).getMessage());
	}
	
	@Test
	public void shouldNotCallLoggerWhenLogLevelIsWarn() throws Throwable {
		// Setup the Logger for this test
		setupLogger(Level.WARN);
		
		ProceedingJoinPoint mock = mock(ProceedingJoinPoint.class);
		MethodSignature mockSignature = mock(MethodSignature.class);
		// Need a real java.lang.reflect.Method
		Method testMethod = this.getClass().getMethod("test");
		
		when(mock.getSignature()).thenReturn(mockSignature);
		when(mockSignature.getMethod()).thenReturn(testMethod);
		when(mock.getTarget()).thenReturn("String");
		
		aspect.log(mock, null);
		
		// Ensure the Logger was not called
		verify(mockAppender, times(0)).doAppend(any(LoggingEvent.class));
	}
	
	public void test() {
		// Required so we can obtain a java.lang.reflect.Method in the test
	}
	
}
