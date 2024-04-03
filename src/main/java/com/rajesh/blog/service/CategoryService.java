package com.rajesh.blog.service;

import java.util.List;

import com.rajesh.blog.dto.CategoryDTO;

public interface CategoryService {

	// create update getbyId getAll delete
	
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
	CategoryDTO getCategory(Integer categoryId);
	List<CategoryDTO> getAllCategory();
	void deleteCategory(Integer categoryId);
	
	
	
	
}
