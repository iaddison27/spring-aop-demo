package org.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.test.entity.Address;

@Repository
public class AddressDao implements IAddressDao {

	@PersistenceContext
    private EntityManager manager;
	
	@Override
	public Address saveOrUpdate(Address address) {
		return manager.merge(address);
	}

	@Override
	public Address getById(long id) {
		return manager.find(Address.class, id);
	}

	@Override
	public List<Address> getAll() {
		Query query = manager.createNamedQuery("Address.findAll");            
        return (List<Address>) query.getResultList();
	}

}
