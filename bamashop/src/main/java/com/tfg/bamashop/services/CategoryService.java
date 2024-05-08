package com.tfg.bamashop.services;

import java.util.List;

import com.tfg.bamashop.dto.CategoryDto;
import com.tfg.bamashop.dto.CategoryRequestDto;
import com.tfg.bamashop.entities.Category;

public interface CategoryService {

	public List<CategoryDto> getCategories();
	
	public CategoryDto getCategory(Long id);

	public CategoryDto saveCategory(CategoryRequestDto categoryRequestDto);
	
	public CategoryDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);
	
	public Category findById(Long id);
	
	public List<CategoryDto> searchByDescription(String description);
	
	public void deleteCategory(Long id);
	
}
