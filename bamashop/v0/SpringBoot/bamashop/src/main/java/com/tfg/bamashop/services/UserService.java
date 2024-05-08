package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.LoginDto;
import com.tfg.bamashop.dto.UserDto;
import com.tfg.bamashop.dto.UserRequestDto;
import com.tfg.bamashop.entities.User;

public interface UserService {
	
	public List<UserDto> getUsers();
	
	public UserDto getUser(Long id);

	public UserDto saveUser(UserRequestDto userRequestDto);
	
	public UserDto updateUser(Long id, UserRequestDto userRequestDto);
	
	public User findByUsername(String username);
	
	public User findById(Long id);
	
	public void deleteUser(Long id);
	
	public User getByUsernameAndPassword(LoginDto loginDto);
}
