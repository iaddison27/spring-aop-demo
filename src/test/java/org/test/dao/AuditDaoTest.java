package org.test.dao;

import static org.junit.Assert.assertEquals;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import org.test.entity.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:repositoryTest-context.xml" })
public class AuditDaoTest {

	@Autowired
	protected AuditDao auditDao;
	
	private Clock clock = Clock.fixed(Instant.EPOCH, ZoneOffset.UTC);
	
	@Test
	@Transactional
    @Rollback(true)
	public final void shouldReturnAllAuditRecords() throws Exception {
		// Test data
		List<Audit> expected = new ArrayList<>();
		
		expected.add(createAndPersistAudit("Math", "add", "2, 3", LocalDateTime.now(clock)));
		expected.add(createAndPersistAudit("Math", "subtract", "10, 4", LocalDateTime.now(clock)));
		expected.add(createAndPersistAudit("AdvancedMath", "add", "2, 3, 4", LocalDateTime.now(clock)));
		expected.add(createAndPersistAudit("AdvancedMath", "multiply", "6, 1", LocalDateTime.now(clock)));
		
		assertEquals(expected, auditDao.getAll());
	}

	private Audit createAndPersistAudit(String className, String methodName, String args, LocalDateTime timestamp) {
		Audit audit = new Audit(className, methodName, args, timestamp);
		auditDao.add(audit);
		return audit;
	}
	
}
