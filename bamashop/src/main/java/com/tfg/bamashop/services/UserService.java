package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.UserDto;
import com.tfg.bamashop.dto.UserRegisterRequestDto;
import com.tfg.bamashop.dto.UserRequestDto;
import com.tfg.bamashop.entities.User;
import com.tfg.security.dto.LoginDto;

public interface UserService {
	
	public List<UserDto> getUsers();
	
	public UserDto getUser(Long id);

	public UserDto saveUser(UserRequestDto userRequestDto);
	
	public UserDto updateUser(Long id, UserRequestDto userRequestDto);
	
	public User findByUsername(String username);
	
	public User findById(Long id);
	
	public void deleteUser(Long id);
	
	public User getByUsernameAndPassword(LoginDto loginDto);
	
	public List<UserDto> getUserByUsername(String username);
	
	public UserDto registerUser(UserRegisterRequestDto userRegisterRequestDto);
}
