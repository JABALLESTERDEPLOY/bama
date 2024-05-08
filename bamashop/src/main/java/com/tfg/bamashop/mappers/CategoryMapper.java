package com.tfg.bamashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.CategoryDto;
import com.tfg.bamashop.dto.CategoryRequestDto;
import com.tfg.bamashop.entities.Category;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	@Mapping(source = "category.id", target = "id")
	@Mapping(source = "category.description", target = "description")
	public CategoryDto entityToDto(Category category);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "categoryDto.description", target = "description")
	@Mapping(target = "products", ignore = true)
	public Category dtoToEntity(CategoryRequestDto categoryDto);
}
