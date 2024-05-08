package com.tfg.bamashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tfg.bamashop.dto.ProductDto;
import com.tfg.bamashop.dto.ProductRequestDto;
import com.tfg.bamashop.entities.Category;
import com.tfg.bamashop.entities.Product;
import com.tfg.bamashop.entities.Supplier;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	@Mapping(source = "product.id", target = "id")
	@Mapping(source = "product.description", target = "description")
	@Mapping(source = "supplier.id", target = "idSupplier")
	@Mapping(source = "product.price", target = "price")
	@Mapping(source = "product.stock", target = "stock")
	@Mapping(source = "category.id", target = "idCategory")
	@Mapping(source = "product.status", target = "status")
	public ProductDto entityToDto(Product product, Supplier supplier, Category category);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "productDto.description", target = "description")
	@Mapping(source = "idSupplier", target = "idSupplier.id")
	@Mapping(source = "productDto.price", target = "price")
	@Mapping(source = "productDto.stock", target = "stock")
	@Mapping(source = "idCategory", target = "idCategory.id")
	@Mapping(source = "productDto.status", target = "status")
	@Mapping(target = "images", ignore = true)
	@Mapping(target = "posts", ignore = true)
	public Product dtoToEntity(ProductRequestDto productDto);

}
