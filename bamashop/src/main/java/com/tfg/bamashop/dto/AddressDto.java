package com.tfg.bamashop.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class AddressDto {

	@Hidden
	private Long id;
	private String phone;
	private Long number;
	private String street;
	private String floor;
	private String zipCode;
	private String city;
	private String province;
	private String country;
	
}
