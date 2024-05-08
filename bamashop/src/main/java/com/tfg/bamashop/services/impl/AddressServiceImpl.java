package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.AddressDto;
import com.tfg.bamashop.dto.AddressRequestDto;
import com.tfg.bamashop.entities.Address;
import com.tfg.bamashop.entities.User;
import com.tfg.bamashop.mappers.AddressMapper;
import com.tfg.bamashop.repositories.AddressRepository;
import com.tfg.bamashop.repositories.UserRepository;
import com.tfg.bamashop.services.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public AddressDto getAddress(Long id) {
		Optional<Address> address = addressRepository.findById(id);

		if (address == null) {
			throw new RuntimeException("Address does not exist");
		}

		AddressDto addressDto = AddressMapper.INSTANCE.entityToDto(address.get());
		return addressDto;
	}

	@Override
	public List<AddressDto> getAddresses() {
		List<Address> addresses = addressRepository.findAll();

		if (addresses.size() < 0) {
			throw new RuntimeException("Adresses don't exist");
		}

		List<AddressDto> addressesDto = new ArrayList<>();

		for (Address address : addresses) {
			AddressDto addressDto = AddressMapper.INSTANCE.entityToDto(address);
			addressesDto.add(addressDto);
		}

		return addressesDto;
	}

	@Override
	public AddressDto saveAddress(AddressRequestDto addressRequestDto) {

		if (addressRequestDto == null) {
			throw new RuntimeException("Address is missing data");
		}

		Address address = AddressMapper.INSTANCE.dtoToEntity(addressRequestDto);

		Address addressRet = addressRepository.save(address);

		return AddressMapper.INSTANCE.entityToDto(addressRet);
	}

	@Override
	public AddressDto updateAddress(Long id, AddressRequestDto addressRequestDto) {
		if (addressRequestDto == null) {
			throw new RuntimeException("Address is missing data");
		}

		Optional<Address> addressExists = addressRepository.findById(id);

		if (addressExists == null) {
			throw new RuntimeException("Address does not exist");
		}

		Address address = AddressMapper.INSTANCE.dtoToEntity(addressRequestDto);
		address.setId(id);

		Address addressRet = addressRepository.save(address);

		return AddressMapper.INSTANCE.entityToDto(addressRet);
	}

	@Override
	public Address findById(Long id) {
		return addressRepository.getById(id);
	}

	public List<Address> searchAddresses(String anyField) {
        return addressRepository.searchAllFields(anyField);
    }
	
	@Override
	public void deleteAddress(Long id) {
		Optional<Address> addressOptional = addressRepository.findById(id);
		if (addressOptional.isPresent()) {
			Address address = addressOptional.get();
			User user = userRepository.findByAddress(address);
			if (user != null) {
				user.setAddress(null);
				userRepository.save(user);
			}
			addressRepository.deleteById(id);
		} else {
			throw new RuntimeException("Address does not exist");
		}
	}

}
