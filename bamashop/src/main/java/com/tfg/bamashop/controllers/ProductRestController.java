package com.tfg.bamashop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.bamashop.dto.ProductDto;
import com.tfg.bamashop.dto.ProductRequestDto;
import com.tfg.bamashop.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "ProductRestController", description = "ProductRestController API")
@SecurityRequirement(name = "bearerAuth")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@Operation(summary = "Get Products", operationId = "getProducts", description = "Get Products")
	@GetMapping("/products")
	public ResponseEntity<Object> getProducts() {

		log.info("Init getProducts - ProductRestController");

		return ResponseEntity.ok(productService.getProducts());
	}

	@Operation(summary = "Get Product", operationId = "getProduct", description = "Get a product")
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable Long id) {

		log.info("Init getProduct - ProductRestController");

		return ResponseEntity.ok(productService.getProduct(id));
	}

	@Operation(summary = "Save Product", operationId = "saveProduct", description = "Save a product")
	@PostMapping("/products")
	public ResponseEntity<Object> saveProduct(@RequestBody ProductRequestDto productRequestDto) {

		log.info("Init saveProduct - ProductRestController");

		ProductDto product = productService.saveProduct(productRequestDto);

		return ResponseEntity.ok(product);
	}

	@Operation(summary = "Update Product", operationId = "updateProduct", description = "Update a product")
	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable Long id,
			@RequestBody ProductRequestDto productRequestDto) {

		log.info("Init updateUser - ProductRestController");

		return ResponseEntity.ok(productService.updateProduct(id, productRequestDto));
	}

	@Operation(summary = "Delete Product", operationId = "deleteProduct", description = "Delete a product")
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {

		log.info("Init deleteProduct - ProductRestController");

		productService.deleteProduct(id);

		return ResponseEntity.ok("Product deleted");
	}

	@Operation(summary = "Update Product Status", operationId = "updateProductStatus", description = "Update a product status")
	@PutMapping("/products/{id}/status/{status}")
	public ResponseEntity<Object> updateProductStatus(@PathVariable Long id, @PathVariable String status) {

		log.info("Init updateProductStatus - ProductRestController");

		productService.updateStatusById(id, status);

		return ResponseEntity.ok("Status updated");
	}
	
	@Operation(summary = "Get Product By Description ", operationId = "getProductByDescription", description = "Get products by description")
	@GetMapping("/products/by-description/{description}")
	public ResponseEntity<Object> getProductByDescription(@PathVariable String description) {

		log.info("Init getProductByDescription - ProductRestController");

		return ResponseEntity.ok(productService.findByDescription(description));
	}
}
