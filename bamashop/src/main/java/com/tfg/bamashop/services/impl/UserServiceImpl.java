package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.UserDto;
import com.tfg.bamashop.dto.UserRegisterRequestDto;
import com.tfg.bamashop.dto.UserRequestDto;
import com.tfg.bamashop.entities.Address;
import com.tfg.bamashop.entities.Role;
import com.tfg.bamashop.entities.User;
import com.tfg.bamashop.mappers.UserMapper;
import com.tfg.bamashop.repositories.AddressRepository;
import com.tfg.bamashop.repositories.RoleRepository;
import com.tfg.bamashop.repositories.UserRepository;
import com.tfg.bamashop.services.UserService;
import com.tfg.security.dto.LoginDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		UserDto userDto = UserMapper.INSTANCE.entityToDto(user.get());
		return userDto;
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> users = userRepository.findAll();
		List<UserDto> usersDto = new ArrayList<>();

		for (User user : users) {
			UserDto userDto = UserMapper.INSTANCE.entityToDto(user);
			usersDto.add(userDto);
		}

		return usersDto;
	}

	@Override
	public UserDto saveUser(UserRequestDto userRequestDto) {
		if (userRequestDto == null) {
			throw new RuntimeException("User is missing data");
		}

		User userExists = userRepository.findByUsername(userRequestDto.getUsername());

		if (userExists != null) {
			throw new RuntimeException("User exists");
		}

		Set<Role> roles = new HashSet<>();

		for (String roleName : userRequestDto.getRoles()) {
			Role role = roleRepository.findByRoleName(roleName);
			if (role != null) {
				roles.add(role);
			}
		}

		User user = UserMapper.INSTANCE.dtoToEntity(userRequestDto,
				passwordEncoder.encode(userRequestDto.getPassword()), roles);

		User userRet = userRepository.save(user);

		return UserMapper.INSTANCE.entityToDto(userRet);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDto updateUser(Long id, UserRequestDto userRequestDto) {
		if (userRequestDto == null) {
			throw new RuntimeException("User is missing data");
		}

		Optional<User> userExists = userRepository.findById(id);

		if (userExists == null) {
			throw new RuntimeException("User does not exist");
		}

		Set<Role> roles = new HashSet<>();

		for (String roleName : userRequestDto.getRoles()) {
			Role role = roleRepository.findByRoleName(roleName);
			if (role != null) {
				roles.add(role);
			}
		}

		if (userRequestDto.getPassword() == "") {
			userRequestDto.setPassword(userExists.get().getPassword());
		} else {
			userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		}

		User user = UserMapper.INSTANCE.dtoToEntity(userRequestDto, userRequestDto.getPassword(), roles);
		user.setId(id);

		User userRet = userRepository.save(user);

		return UserMapper.INSTANCE.entityToDto(userRet);
	}

	@Override
	public User findById(Long id) {
		return userRepository.getById(id);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getByUsernameAndPassword(LoginDto loginDto) {
		User user = userRepository.findByUsername(loginDto.getUsername());
		if (user == null) {
			throw new RuntimeException("User is empty or password is not correct");
		}
		if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Password is not correct");
		}
		return user;
	}

	@Override
	public List<UserDto> getUserByUsername(String username) {
		List<User> users = userRepository.getUserByUsername(username);
		List<UserDto> usersDto = new ArrayList<>();

		for (User user : users) {
			UserDto userDto = UserMapper.INSTANCE.entityToDto(user);
			usersDto.add(userDto);
		}

		return usersDto;
	}

	@Override
	public UserDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {

		Address address = new Address();
		address.setCity(userRegisterRequestDto.getCity());
		address.setCountry(userRegisterRequestDto.getCountry());
		address.setFloor(userRegisterRequestDto.getFloor());
		address.setNumber(userRegisterRequestDto.getNumber());
		address.setPhone(userRegisterRequestDto.getPhone());
		address.setProvince(userRegisterRequestDto.getProvince());
		address.setStreet(userRegisterRequestDto.getStreet());
		address.setZipCode(userRegisterRequestDto.getZipCode());

		Address addressRet = addressRepository.save(address);

		if (addressRet == null) {
			throw new RuntimeException("Address is null");
		}

		User userExists = userRepository.findByUsername(userRegisterRequestDto.getUsername());

		if (userExists != null) {
			throw new RuntimeException("User exists");
		}

		Role roleUser = roleRepository.findByRoleName("ROLE_USER");
		Role roleClient = roleRepository.findByRoleName("ROLE_CLIENT");

		Set<Role> roles = new HashSet<>();
		roles.add(roleUser);
		roles.add(roleClient);

		Set<String> roleNames = new HashSet<>();
		roleNames.add(roleUser.getRoleName());
		roleNames.add(roleClient.getRoleName());

		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail(userRegisterRequestDto.getEmail());
		userRequestDto.setFirstname(userRegisterRequestDto.getFirstname());
		userRequestDto.setIdAddress(addressRet.getId());
		userRequestDto.setLastname(userRegisterRequestDto.getLastname());
		userRequestDto.setPassword(userRegisterRequestDto.getPassword());
		userRequestDto.setUsername(userRegisterRequestDto.getUsername());
		userRequestDto.setRoles(roleNames);

		User user = UserMapper.INSTANCE.dtoToEntity(userRequestDto,
				passwordEncoder.encode(userRequestDto.getPassword()), roles);

		User userRet = userRepository.save(user);

		return UserMapper.INSTANCE.entityToDto(userRet);
	}

}
