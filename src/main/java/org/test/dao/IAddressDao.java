package org.test.dao;

import java.util.List;

import org.test.entity.Address;

public interface IAddressDao {

	public Address saveOrUpdate(Address address);
	
	public Address getById(long id);
	
	public List<Address> getAll();
}
