package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.AddressDto;
import com.tfg.bamashop.dto.AddressRequestDto;
import com.tfg.bamashop.entities.Address;

public interface AddressService {
	
	public List<AddressDto> getAddresses();
	
	public AddressDto getAddress(Long id);

	public AddressDto saveAddress(AddressRequestDto addressRequestDto);
	
	public AddressDto updateAddress(Long id, AddressRequestDto addressRequestDto);
	
	public Address findById(Long id);
	
	public List<Address> searchAddresses(String anyField);
	
	public void deleteAddress(Long id);
}
