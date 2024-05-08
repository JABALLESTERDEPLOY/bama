package com.tfg.bamashop.utils;

import java.util.Set;

import com.tfg.bamashop.dto.UserDto;
import com.tfg.bamashop.dto.UserRequestDto;
import com.tfg.bamashop.entities.Address;
import com.tfg.bamashop.entities.Role;
import com.tfg.bamashop.entities.User;

public class TestUtils {

	public User createUser(Long id, String firstname, String lastname, String username, String password, String email,
			Set<Role> roles, Address address) {
		User user = new User();
		user.setFirstname(firstname);
		user.setAddress(address);
		user.setEmail(email);
		user.setId(id);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setRoles(roles);
		user.setUsername(username);
		return user;
	}

	public UserDto createUserDto(Long id, String firstname, String lastname, String username, String password,
			String email, Set<String> roles, Long idAddress) {
		UserDto user = new UserDto();
		user.setFirstname(firstname);
		user.setIdAddress(idAddress);
		user.setEmail(email);
		user.setId(id);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setRoles(roles);
		user.setUsername(username);
		return user;
	}

	public UserRequestDto createUserRequestDto(Long id, String firstname, String lastname, String username,
			String password, String email, Set<String> roles, Long idAddress) {
		UserRequestDto user = new UserRequestDto();
		user.setFirstname(firstname);
		user.setIdAddress(idAddress);
		user.setEmail(email);
		user.setId(id);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setRoles(roles);
		user.setUsername(username);
		return user;
	}

	public Role createRole(Long id, String roleName) {
		Role role = new Role();
		role.setId(id);
		role.setRoleName(roleName);
		return role;
	}

	public Address createAddress(Long id, String phone, Long number, String street, String floor, String zipCode,
			String city, String province, String country) {
		Address address = new Address();
		address.setCity(city);
		address.setCountry(country);
		address.setFloor(floor);
		address.setId(id);
		address.setNumber(number);
		address.setPhone(phone);
		address.setProvince(province);
		address.setStreet(street);
		address.setZipCode(zipCode);
		return address;
	}

}
