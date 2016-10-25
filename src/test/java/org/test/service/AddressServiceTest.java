package org.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.dao.IAddressDao;
import org.test.entity.Address;


@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

	@Mock
	private IAddressDao addressDao;
	
	@InjectMocks
	private AddressService addressService;
	
	@Test
	public void shouldReturnAllAddresses() {
		List<Address> expected = new ArrayList<>();
		expected.add(new Address("1", "Market Street", "NE3 1AA"));
		expected.add(new Address("20a", "Town Road", "NE1 1TT"));
		expected.add(new Address("100", "Beach Road", "NE22 2DD"));
		
		when(addressDao.getAll()).thenReturn(expected);
		
		assertEquals(expected, addressService.getAll());
		
		verify(addressDao, times(1)).getAll();
	}
	
	@Test
	public void shouldReturnAddressWithTheSpecifiedId() {
		Address expected = new Address("1", "Market Street", "NE3 1AA");
		
		when(addressDao.getById(2L)).thenReturn(expected);
		
		assertEquals(expected, addressService.getById(2L));
		
		verify(addressDao, times(1)).getById(2L);
	}

}
