package com.tfg.bamashop.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class ImageDto {

	@Hidden
	private Long id;
	private String url;
	private Long idProduct;
}
