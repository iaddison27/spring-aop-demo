package org.test.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.test.dao.IAuditDao;
import org.test.entity.Audit;

@Service
public class AuditService implements IAuditService {

	@Autowired
	private IAuditDao auditDao;
	
	@Override
	@Transactional
	public void audit(String className, String methodName, Object[] args, LocalDateTime timestamp) {
		auditDao.add(new Audit(className, methodName, getArgsString(args), timestamp));
	}
	
	@Override
	public List<Audit> getAll() {
		return auditDao.getAll();
	}
	
	private String getArgsString(Object[] args) {
		String argsString = "";
		for (Object object : args) {
			argsString += object + ", ";
		}
		if (argsString.length() > 2) {
			argsString = argsString.substring(0, argsString.length() - 2);
		}
		return argsString;
	}
}
