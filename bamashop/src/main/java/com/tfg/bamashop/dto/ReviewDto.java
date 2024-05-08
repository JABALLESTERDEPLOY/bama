package com.tfg.bamashop.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class ReviewDto {

	@Hidden
	private Long id;
	private String comment;
	private Integer rating;
	private Long idPost;
	private Long idUser;
}
