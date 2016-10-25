package org.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.dao.IAddressDao;
import org.test.entity.Address;

@Service
public class AddressService implements IAddressService {

	@Autowired
	private IAddressDao addressDao;
	
	@Override
	@Transactional
	public Address saveOrUpdate(Address address) {
		return addressDao.saveOrUpdate(address);
	}
	
	@Override
	public Address getById(long id) {
		return addressDao.getById(id);
	}
	
	@Override
	public List<Address> getAll() {
		return addressDao.getAll();
	}
}
