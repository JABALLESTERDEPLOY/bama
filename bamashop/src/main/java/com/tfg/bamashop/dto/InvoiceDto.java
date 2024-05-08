package com.tfg.bamashop.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class InvoiceDto {

	@Hidden
	private Long id;
	private LocalDate invoiceDate;
	private Long idPost;
	private Long idBuyer;
}
