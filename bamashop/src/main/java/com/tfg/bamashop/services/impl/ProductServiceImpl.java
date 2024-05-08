package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.ProductDto;
import com.tfg.bamashop.dto.ProductRequestDto;
import com.tfg.bamashop.entities.Category;
import com.tfg.bamashop.entities.Product;
import com.tfg.bamashop.entities.Supplier;
import com.tfg.bamashop.mappers.ProductMapper;
import com.tfg.bamashop.repositories.CategoryRepository;
import com.tfg.bamashop.repositories.ProductRepository;
import com.tfg.bamashop.repositories.SupplierRepository;
import com.tfg.bamashop.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<ProductDto> getProducts() {

		List<Product> products = productRepository.findAll();

		if (products.size() < 0) {
			throw new RuntimeException("Product don't exist");
		}

		List<ProductDto> productsDto = new ArrayList<>();

		for (Product product : products) {
			Supplier supplier = supplierRepository.getById(product.getIdSupplier().getId());
			Category category = categoryRepository.getById(product.getIdCategory().getId());
			ProductDto productDto = ProductMapper.INSTANCE.entityToDto(product, supplier, category);
			productsDto.add(productDto);
		}

		return productsDto;
	}

	@Override
	public ProductDto getProduct(Long id) {

		Product product = productRepository.getById(id);

		if (product == null) {
			throw new RuntimeException("Product does not exist");
		}

		Supplier supplier = supplierRepository.getById(product.getIdSupplier().getId());
		Category category = categoryRepository.getById(product.getIdCategory().getId());

		ProductDto productDto = ProductMapper.INSTANCE.entityToDto(product, supplier, category);
		return productDto;
	}

	@Override
	public ProductDto saveProduct(ProductRequestDto productRequestDto) {

		if (productRequestDto == null) {
			throw new RuntimeException("Product is missing data");
		}

		Product product = ProductMapper.INSTANCE.dtoToEntity(productRequestDto);

		Product productRet = productRepository.save(product);

		Supplier supplier = supplierRepository.getById(product.getIdSupplier().getId());
		Category category = categoryRepository.getById(product.getIdCategory().getId());

		return ProductMapper.INSTANCE.entityToDto(productRet, supplier, category);
	}

	@Override
	public ProductDto updateProduct(Long id, ProductRequestDto productRequestDto) {

		if (productRequestDto == null) {
			throw new RuntimeException("Product is missing data");
		}

		Optional<Product> productExists = productRepository.findById(id);

		if (productExists == null) {
			throw new RuntimeException("Product does not exist");
		}

		Product product = ProductMapper.INSTANCE.dtoToEntity(productRequestDto);
		product.setId(id);

		Product productRet = productRepository.save(product);

		Supplier supplier = supplierRepository.getById(product.getIdSupplier().getId());
		Category category = categoryRepository.getById(product.getIdCategory().getId());

		return ProductMapper.INSTANCE.entityToDto(productRet, supplier, category);
	}

	@Override
	public Product findById(Long id) {

		return productRepository.getById(id);
	}
	
	@Override
	public List<ProductDto> findByDescription(String description) {
		List<Product> products = productRepository.searchByDescription(description);
		List<ProductDto> productsDto = new ArrayList<>();

		for (Product product : products) {
			ProductDto productDto = ProductMapper.INSTANCE.entityToDto(product, product.getIdSupplier(), product.getIdCategory());
			productsDto.add(productDto);
		}

		return productsDto;
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void updateStatusById(Long id, String status) {
		Boolean active = true;

		if (status.equals("1")) {
			active = true;
		} else {
			active = false;
		}

		productRepository.updateStatusById(active, id);

	}
}
