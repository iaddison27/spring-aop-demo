package org.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import org.test.entity.Audit;

@Repository
public class AuditDao implements IAuditDao {

	@PersistenceContext
    private EntityManager manager;

	@Override
	public void add(Audit audit) {
		manager.persist(audit);
	}
	
	@Override
	public List<Audit> getAll() {
		Query query = manager.createNamedQuery("Audit.findAll");            
        return (List<Audit>) query.getResultList();
	}

}
