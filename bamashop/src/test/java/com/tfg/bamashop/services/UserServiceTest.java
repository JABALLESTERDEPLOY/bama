package com.tfg.bamashop.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tfg.bamashop.dto.UserDto;
import com.tfg.bamashop.dto.UserRequestDto;
import com.tfg.bamashop.entities.Address;
import com.tfg.bamashop.entities.Role;
import com.tfg.bamashop.entities.User;
import com.tfg.bamashop.repositories.RoleRepository;
import com.tfg.bamashop.repositories.UserRepository;
import com.tfg.bamashop.services.impl.UserServiceImpl;
import com.tfg.bamashop.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private List<User> userList;

	private List<UserDto> userDtoList;

	private List<UserRequestDto> userRequestDtoList;

	@BeforeEach
	void setUp() throws Exception {

		this.userList = new ArrayList<>();
		this.userDtoList = new ArrayList<>();
		this.userRequestDtoList = new ArrayList<>();

		TestUtils testUtils = new TestUtils();

		Set<Role> roles = new HashSet<>();
		Set<String> roleString = new HashSet<>();
		Role role1 = testUtils.createRole(1L, "admin");

		Address address1 = testUtils.createAddress(1L, "123456789", 12L, "Street 123", "1ºA", "06008", "City",
				"Province", "Country");
		Address address2 = testUtils.createAddress(2L, "123456782", 33L, "Street 456", "3ºB", "06001", "Cita",
				"Provinca", "Countra");

		roleString.add(role1.getRoleName());
		roles.add(role1);

		User user1 = testUtils.createUser(1L, "user", "user", "user", "user", "user", roles, address1);
		User user2 = testUtils.createUser(2L, "usera", "usera", "usera", "usera", "usera", roles, address2);
		this.userList.add(user1);
		this.userList.add(user2);

		UserDto userDto1 = testUtils.createUserDto(1L, "user", "user", "user", "user", "user", roleString,
				address1.getId());
		UserDto userDto2 = testUtils.createUserDto(2L, "usera", "usera", "usera", "usera", "usera", roleString,
				address2.getId());
		this.userDtoList.add(userDto1);
		this.userDtoList.add(userDto2);

		UserRequestDto userRequestDto1 = testUtils.createUserRequestDto(1L, "user", "user", "user", "user", "user",
				roleString, address1.getId());
		UserRequestDto userRequestDto2 = testUtils.createUserRequestDto(2L, "usera", "usera", "usera", "usera", "usera",
				roleString, address2.getId());
		this.userRequestDtoList.add(userRequestDto1);
		this.userRequestDtoList.add(userRequestDto2);

	}

	@Test
	void testGetUser() {
		User user = this.userList.get(0);

		Optional<User> userOpt = Optional.of(user);

		when(this.userRepository.findById(user.getId())).thenReturn(userOpt);

		UserDto userDto = this.userServiceImpl.getUser(user.getId());
		assertThat(userDto).isNotNull();
	}

	@Test
	void testGetUsers() {

		when(this.userRepository.findAll()).thenReturn(this.userList);

		this.userDtoList = this.userServiceImpl.getUsers();
		assertThat(this.userDtoList).isNotEmpty();
	}
}
