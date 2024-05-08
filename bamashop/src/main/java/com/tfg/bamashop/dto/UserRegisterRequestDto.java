package com.tfg.bamashop.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class UserRegisterRequestDto {

	@Hidden
	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private String phone;
	private Long number;
	private String street;
	private String floor;
	private String zipCode;
	private String city;
	private String province;
	private String country;
}
