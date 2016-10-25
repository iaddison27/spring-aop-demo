package org.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.entity.Address;
import org.test.service.IAddressService;

@Controller
@RequestMapping(value = "/address")
public class AddressController {

	@Autowired
	private IAddressService addressService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Address> list() {
		return addressService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Address getById(@PathVariable("id") final long id) {
		return addressService.getById(id);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public boolean create(@RequestBody Address address) {
		addressService.saveOrUpdate(address);
		return true;
	}
	
	
}
