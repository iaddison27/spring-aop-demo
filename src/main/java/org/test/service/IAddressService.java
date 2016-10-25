package org.test.service;

import java.util.List;

import org.test.entity.Address;

public interface IAddressService {

	public Address saveOrUpdate(Address address);
	
	public Address getById(long id);
	
	public List<Address> getAll();
}
