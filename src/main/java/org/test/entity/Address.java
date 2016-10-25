package org.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ADDRESS")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", 
    	query = "SELECT a FROM Address a order by a.id"
    )
})
public class Address {

	@Id
	@GeneratedValue
	@Column(name = "ADDRESS_ID")
	private long id;
	
	@Version
	private long version;

	@Column(name = "HOUSE_NUMBER")
	private String number;
	
	@Column(name = "STREET")
	private String street;

	@Column(name = "POSTCODE")
	private String postcode;
	
	public Address() {
		super();
	}

	public Address(String number, String street, String postcode) {
		super();
		this.number = number;
		this.street = street;
		this.postcode = postcode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
}
