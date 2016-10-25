package org.test.controller;

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
import org.test.entity.Address;
import org.test.service.AddressService;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {
	
	@Mock
	private AddressService addressService;
	
	@InjectMocks
	private AddressController addressController;
	
	@Test
	public void shouldReturnAllAddresses() {
		List<Address> expected = new ArrayList<>();
		expected.add(new Address("1", "Market Street", "NE3 1AA"));
		expected.add(new Address("20a", "Town Road", "NE1 1TT"));
		expected.add(new Address("100", "Beach Road", "NE22 2DD"));
		
		when(addressService.getAll()).thenReturn(expected);
		
		assertEquals(expected, addressController.list());
		
		verify(addressService, times(1)).getAll();
	}
	
	@Test
	public void shouldReturnAddressWithTheSpecifiedId() {
		Address expected = new Address("1", "Market Street", "NE3 1AA");
		
		when(addressService.getById(2L)).thenReturn(expected);
		
		assertEquals(expected, addressController.getById(2L));
		
		verify(addressService, times(1)).getById(2L);
	}
}
