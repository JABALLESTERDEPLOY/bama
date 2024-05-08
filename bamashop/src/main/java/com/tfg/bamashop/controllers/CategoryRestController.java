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

import com.tfg.bamashop.dto.CategoryDto;
import com.tfg.bamashop.dto.CategoryRequestDto;
import com.tfg.bamashop.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "CategoryRestController", description = "CategoryRestController API")
@SecurityRequirement(name = "bearerAuth")
public class CategoryRestController {

	@Autowired
	CategoryService categoryService;

	@Operation(summary = "Get Categories", operationId = "getCategories", description = "Get categories")
	@GetMapping("/categories")
	public ResponseEntity<Object> getCategories() {

		log.info("Init getCategories - CategoryRestController");

		return ResponseEntity.ok(categoryService.getCategories());
	}

	@Operation(summary = "Get Category", operationId = "getCategory", description = "Get a category")
	@GetMapping("/categories/{id}")
	public ResponseEntity<Object> getCategory(@PathVariable Long id) {

		log.info("Init getCategory - CategoryRestController");

		return ResponseEntity.ok(categoryService.getCategory(id));
	}

	@Operation(summary = "Save Category", operationId = "saveCategory", description = "Save a category")
	@PostMapping("/categories")
	public ResponseEntity<Object> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {

		log.info("Init saveCategory - CategoryRestController");

		CategoryDto categoryDto = categoryService.saveCategory(categoryRequestDto);

		return ResponseEntity.ok(categoryDto);
	}

	@Operation(summary = "Update Category", operationId = "updateCategory", description = "Update a category")
	@PutMapping("/categories/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable Long id,
			@RequestBody CategoryRequestDto categoryRequestDto) {

		log.info("Init updateCategory - CategoryRestController");

		return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequestDto));
	}

	@Operation(summary = "Delete Category", operationId = "deleteCategory", description = "Delete a category")
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {

		log.info("Init deleteCategory - CategoryRestController");

		categoryService.deleteCategory(id);

		return ResponseEntity.ok("Category deleted");
	}
	
	@Operation(summary = "Get Category By Description ", operationId = "getCategoryByDescription", description = "Get categories by description")
	@GetMapping("/categories/by-description/{description}")
	public ResponseEntity<Object> getCategoryByDescription(@PathVariable String description) {

		log.info("Init getCategoryByDescription - CategoryRestController");

		return ResponseEntity.ok(categoryService.searchByDescription(description));
	}
}
