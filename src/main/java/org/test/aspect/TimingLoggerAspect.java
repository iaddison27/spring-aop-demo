package org.test.aspect;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.test.aspect.annotation.TimingLogging;

@Aspect
@Component
public class TimingLoggerAspect {

	private static final Logger LOGGER = Logger.getLogger(TimingLoggerAspect.class);
	
	private Clock clock;
	
	/**
	 * Default constructor. Uses a UTC java.time.Clock
	 */
	public TimingLoggerAspect() {
		this(Clock.systemUTC());
	}
	
	/**
	 * Overridden constructor allowing a java.time.Clock object to be provided. Useful for unit testing
	 * @param clock  the java.time.Clock to use (may be a mock object)
	 */
	public TimingLoggerAspect(Clock clock) {
		this.clock = clock;
	}

	@Around("@annotation(timingLogging)")
	public Object log(final ProceedingJoinPoint joinPoint, TimingLogging timingLogging) throws Throwable {

		// Get method details
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String inspectedClassName = joinPoint.getTarget().getClass().getSimpleName();
		String inspectedMethodName = signature.getMethod().getName();

		// Log before method called
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("[" + inspectedClassName + "." + inspectedMethodName + "][Start:" + LocalDateTime.now(clock).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "]");
		}

		// Call method
		Object output = joinPoint.proceed();

		// Log after method called
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("[" + inspectedClassName + "." + inspectedMethodName + "][Finish:" + LocalDateTime.now(clock).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "]");
		}

		return output;
	}

}
