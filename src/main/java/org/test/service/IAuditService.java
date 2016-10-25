package org.test.service;

import java.time.LocalDateTime;
import java.util.List;

import org.test.entity.Audit;

public interface IAuditService {

	public void audit(String className, String methodName, Object[] args, LocalDateTime timestamp);
	
	public List<Audit> getAll();
	
}
