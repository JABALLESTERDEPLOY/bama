package com.tfg.bamashop.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class RoleRequestDto {

	@Hidden
	private Long id;
	private String roleName;
}
