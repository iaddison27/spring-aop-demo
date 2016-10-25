package org.test.aspect;

import java.time.Clock;
import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.test.service.IAuditService;

@Aspect
public class AuditAspect {

	@Autowired
	private IAuditService auditService;
	
	private Clock clock;
	
	/**
	 * Default constructor. Uses a UTC java.time.Clock
	 */
	public AuditAspect() {
		this(Clock.systemUTC());
	}
	
	/**
	 * Overridden constructor allowing a java.time.Clock object to be provided. Useful for unit testing
	 * @param clock  the java.time.Clock to use (may be a mock object)
	 */
	public AuditAspect(Clock clock) {
		this.clock = clock;
	}
	
	@After("execution(* org.test.controller.*.*(..))")
    public void anyMethod(JoinPoint joinPoint) 
    {
		// Called after completion of any method in the org.test.controller package but not subpackages
        String className = joinPoint.getTarget().getClass().toString();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        auditService.audit(className, methodName, args, LocalDateTime.now(clock));
    }
	
}
