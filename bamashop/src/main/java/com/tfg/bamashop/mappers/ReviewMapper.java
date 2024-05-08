package com.tfg.bamashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.ReviewDto;
import com.tfg.bamashop.dto.ReviewRequestDto;
import com.tfg.bamashop.entities.Post;
import com.tfg.bamashop.entities.Review;
import com.tfg.bamashop.entities.User;

@Mapper
public interface ReviewMapper {

	ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

	@Mapping(source = "review.id", target = "id")
	@Mapping(source = "review.comment", target = "comment")
	@Mapping(source = "review.rating", target = "rating")
	@Mapping(source = "post.id", target = "idPost")
	@Mapping(source = "user.id", target = "idUser")
	public ReviewDto entityToDto(Review review, Post post, User user);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "reviewDto.comment", target = "comment")
	@Mapping(source = "reviewDto.rating", target = "rating")
	@Mapping(source = "reviewDto.idPost", target = "idPost.id")
	@Mapping(source = "reviewDto.idUser", target = "idUser.id")
	public Review dtoToEntity(ReviewRequestDto reviewDto);
}
