package com.tfg.bamashop.dto;

import java.util.Set;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class UserRequestDto {

	@Hidden
	private Long id;
	private String username;
	private String password;
	private String email;
	public Set<String> roles;
}
