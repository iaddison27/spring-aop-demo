package org.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.test.entity.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:repositoryTest-context.xml" })
public class AddressDaoTest {

	@Autowired
	protected AddressDao addressDao;
	
	@Test
	@Transactional
    @Rollback(true)
	public final void shouldReturnAllAddresses() throws Exception {
		// Test data
		List<Address> expected = new ArrayList<>();
		
		expected.add(createAndPersistAddress("1", "Market Street", "NE3 1AA"));
		expected.add(createAndPersistAddress("20a", "Town Road", "NE1 1TT"));
		expected.add(createAndPersistAddress("100", "Beach Road", "NE22 2DD"));
		
		assertEquals(expected, addressDao.getAll());
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public final void shouldReturnAddressWithTheSpecifiedId() throws Exception {
		// Test data
		List<Address> expected = new ArrayList<>();
		
		expected.add(createAndPersistAddress("1", "Market Street", "NE3 1AA"));
		expected.add(createAndPersistAddress("20a", "Town Road", "NE1 1TT"));
		expected.add(createAndPersistAddress("100", "Beach Road", "NE22 2DD"));
		
		assertEquals(expected.get(1), addressDao.getById(expected.get(1).getId()));
	}

	private Address createAndPersistAddress(String number, String street, String postcode) {
		Address employee = new Address(number, street, postcode);
		return addressDao.saveOrUpdate(employee);
	}
	
}
