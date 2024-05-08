package com.tfg.bamashop.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.bamashop.dto.CategoryDto;
import com.tfg.bamashop.dto.CategoryRequestDto;
import com.tfg.bamashop.entities.Category;
import com.tfg.bamashop.mappers.CategoryMapper;
import com.tfg.bamashop.repositories.CategoryRepository;
import com.tfg.bamashop.services.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepository.findAll();

		if (categories.size() < 0) {
			throw new RuntimeException("Category don't exist");
		}

		List<CategoryDto> categoriesDto = new ArrayList<>();

		for (Category category : categories) {
			CategoryDto categoryDto = CategoryMapper.INSTANCE.entityToDto(category);
			categoriesDto.add(categoryDto);
		}

		return categoriesDto;
	}

	@Override
	public CategoryDto getCategory(Long id) {
		Optional<Category> category = categoryRepository.findById(id);

		if (category == null) {
			throw new RuntimeException("Category does not exist");
		}

		CategoryDto categoryDto = CategoryMapper.INSTANCE.entityToDto(category.get());
		return categoryDto;
	}

	@Override
	public CategoryDto saveCategory(CategoryRequestDto categoryRequestDto) {

		if (categoryRequestDto == null) {
			throw new RuntimeException("Category is missing data");
		}

		Category category = CategoryMapper.INSTANCE.dtoToEntity(categoryRequestDto);

		Category categoryRet = categoryRepository.save(category);

		return CategoryMapper.INSTANCE.entityToDto(categoryRet);
	}

	@Override
	public CategoryDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {

		if (categoryRequestDto == null) {
			throw new RuntimeException("Category is missing data");
		}

		Optional<Category> categoryExists = categoryRepository.findById(id);

		if (categoryExists == null) {
			throw new RuntimeException("Category does not exist");
		}

		Category category = CategoryMapper.INSTANCE.dtoToEntity(categoryRequestDto);
		category.setId(id);

		Category categoryRet = categoryRepository.save(category);

		return CategoryMapper.INSTANCE.entityToDto(categoryRet);
	}

	@Override
	public Category findById(Long id) {

		return categoryRepository.getById(id);
	}
	
	@Override
	public List<CategoryDto> searchByDescription(String description) {
		List<Category> categories = categoryRepository.searchByDescription(description);
		List<CategoryDto> categoriesDto = new ArrayList<>();

		for (Category category : categories) {
			CategoryDto categoryDto = CategoryMapper.INSTANCE.entityToDto(category);
			categoriesDto.add(categoryDto);
		}

		return categoriesDto;
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

}
