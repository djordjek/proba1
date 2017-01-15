package jwd.wafepa.web.dto;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Address;

public class AddressDTO {
	
	private Long id;
	private String street;
	private String number;
	
	public AddressDTO(Long id, String street, String number) {
		super();
		this.id = id;
		this.street = street;
		this.number = number;
	}
	public AddressDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AddressDTO(Address address){
		this.id = address.getId();
		this.street = address.getStreet();
		this.number = address.getNumber();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

}
