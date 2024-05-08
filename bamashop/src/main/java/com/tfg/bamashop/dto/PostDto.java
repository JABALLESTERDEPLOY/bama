package com.tfg.bamashop.dto;

import java.time.LocalDate;
import java.util.Set;

import com.tfg.bamashop.enums.ProductCondition;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class PostDto {

	@Hidden
	private Long id;
	private String title;
	private String description;
	private Double price;
	private ProductCondition productCondition;
	private LocalDate postedDate;
	private Long idCategory;
	private Long idUser;
	public Set<Long> images;
	private Long idProduct;
}
