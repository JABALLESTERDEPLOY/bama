package com.tfg.bamashop.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.UserDto;
import com.tfg.bamashop.dto.UserRequestDto;
import com.tfg.bamashop.entities.Address;
import com.tfg.bamashop.entities.Role;
import com.tfg.bamashop.entities.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "user.id", target = "id")
	@Mapping(source = "user.username", target = "username")
	@Mapping(source = "user.firstname", target = "firstname")
	@Mapping(source = "user.lastname", target = "lastname")
	@Mapping(source = "user.password", target = "password")
	@Mapping(source = "user.email", target = "email")
	@Mapping(source = "address", target = "idAddress")
	@Mapping(source = "roles", target = "roles")
	public UserDto entityToDto(User user);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "userDto.username", target = "username")
	@Mapping(source = "userDto.firstname", target = "firstname")
	@Mapping(source = "userDto.lastname", target = "lastname")
	@Mapping(source = "passwordEncoded", target = "password")
	@Mapping(source = "userDto.email", target = "email")
	@Mapping(source = "userDto.idAddress", target = "address.id")
	@Mapping(source = "roles", target = "roles")
	@Mapping(target = "posts", ignore = true)
	@Mapping(target = "invoices", ignore = true)
	@Mapping(target = "reviews", ignore = true)
	public User dtoToEntity(UserRequestDto userDto, String passwordEncoded, Set<Role> roles);

	default Set<String> rolesToRoleNames(Set<Role> roles) {
		Set<String> roleNames = null;
		if (roles != null) {
			roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
		}
		return roleNames;
	}

	default Long mapAddressToId(Address address) {
		return address != null ? address.getId() : null;
	}
}
