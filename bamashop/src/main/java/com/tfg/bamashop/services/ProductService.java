package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.ProductDto;
import com.tfg.bamashop.dto.ProductRequestDto;
import com.tfg.bamashop.entities.Product;

public interface ProductService {

	public List<ProductDto> getProducts();

	public ProductDto getProduct(Long id);

	public ProductDto saveProduct(ProductRequestDto productRequestDto);

	public ProductDto updateProduct(Long id, ProductRequestDto productRequestDto);

	public Product findById(Long id);
	
	public List<ProductDto> findByDescription(String description);

	void deleteProduct(Long id);

	void updateStatusById(Long id, String status);
}
