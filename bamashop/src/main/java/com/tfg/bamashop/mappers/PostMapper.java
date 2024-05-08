package com.tfg.bamashop.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.PostDto;
import com.tfg.bamashop.dto.PostRequestDto;
import com.tfg.bamashop.entities.Category;
import com.tfg.bamashop.entities.Image;
import com.tfg.bamashop.entities.Post;
import com.tfg.bamashop.entities.Product;
import com.tfg.bamashop.entities.User;

@Mapper
public interface PostMapper {

	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

	@Mapping(source = "post.id", target = "id")
	@Mapping(source = "post.title", target = "title")
	@Mapping(source = "post.description", target = "description")
	@Mapping(source = "post.price", target = "price")
	@Mapping(source = "post.postedDate", target = "postedDate")
	@Mapping(source = "category.id", target = "idCategory")
	@Mapping(source = "user.id", target = "idUser")
	@Mapping(source = "product.id", target = "idProduct")
	@Mapping(source = "post.images", target = "images")
	public PostDto entityToDto(Post post, Category category, User user, Product product);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "invoices", ignore = true)
	@Mapping(target = "reviews", ignore = true)
	@Mapping(source = "postDto.title", target = "title")
	@Mapping(source = "postDto.description", target = "description")
	@Mapping(source = "postDto.price", target = "price")
	@Mapping(source = "postDto.postedDate", target = "postedDate")
	@Mapping(source = "postDto.idCategory", target = "idCategory.id")
	@Mapping(source = "postDto.idUser", target = "idSeller.id")
	@Mapping(source = "postDto.idProduct", target = "idProduct.id")
	@Mapping(source = "images", target = "images")
	public Post dtoToEntity(PostRequestDto postDto, Set<Image> images);

	default Set<Long> postsMap(Set<Image> images) {
		Set<Long> idImages = null;
		if (images != null) {
			idImages = images.stream().map(Image::getId).collect(Collectors.toSet());
		}
		return idImages;
	}
}
