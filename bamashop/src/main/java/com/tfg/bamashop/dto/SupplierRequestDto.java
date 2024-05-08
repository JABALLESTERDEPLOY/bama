package com.tfg.bamashop.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class SupplierRequestDto {

	@Hidden
	private Long id;
	private String companyName;
	private Long idAddress;
}
