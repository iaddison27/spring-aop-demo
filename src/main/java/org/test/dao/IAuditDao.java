package org.test.dao;

import java.util.List;

import org.test.entity.Audit;

public interface IAuditDao {

	public void add(Audit audit);
	
	public List<Audit> getAll();
}
