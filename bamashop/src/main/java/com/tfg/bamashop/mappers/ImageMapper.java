package com.tfg.bamashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.ImageDto;
import com.tfg.bamashop.dto.ImageRequestDto;
import com.tfg.bamashop.entities.Image;
import com.tfg.bamashop.entities.Product;

@Mapper
public interface ImageMapper {

	ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

	@Mapping(source = "image.id", target = "id")
	@Mapping(source = "image.url", target = "url")
	@Mapping(source = "product.id", target = "idProduct")
	public ImageDto entityToDto(Image image, Product product);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "imageDto.url", target = "url")
	@Mapping(source = "imageDto.idProduct", target = "idProduct.id")
	public Image dtoToEntity(ImageRequestDto imageDto);
}
