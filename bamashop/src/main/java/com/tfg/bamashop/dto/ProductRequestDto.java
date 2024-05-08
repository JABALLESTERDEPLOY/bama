package com.tfg.bamashop.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class ProductRequestDto {

	@Hidden
	private Long id;
	private String description;
	private Long idSupplier;
	private Double price;
	private Integer stock;
	private Long idCategory;
	private Boolean status = true;
}
