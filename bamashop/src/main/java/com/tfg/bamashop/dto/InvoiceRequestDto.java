package com.tfg.bamashop.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class InvoiceRequestDto {

	@Hidden
	private Long id;
	private Long idPost;
	private Long idBuyer;
}
