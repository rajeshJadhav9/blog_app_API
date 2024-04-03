package com.rajesh.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajesh.blog.dto.CategoryDTO;
import com.rajesh.blog.entities.Category;
import com.rajesh.blog.exception.ResourseNotFoundException;
import com.rajesh.blog.repositories.CategoryRepo;
import com.rajesh.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	//add repo and model mapper
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category cat =this.modelMapper.map(categoryDTO, Category.class);
		Category addedCat =	this.categoryRepo.save(cat);	
		return this.modelMapper.map(addedCat, CategoryDTO.class);

	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(
				() -> new ResourseNotFoundException("category", "categoryId", categoryId)  );
		cat.setCategoryTitle(categoryDTO.getCategoryTitle());
		cat.setCategoryDescription(categoryDTO.getCategoryDescription());
		
		Category updatedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(
						()-> new ResourseNotFoundException("category", "categoryId", categoryId) );		
		return this.modelMapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> cat = this.categoryRepo.findAll();
		List<CategoryDTO> catDTOs = cat.stream().map( ( cat1 ) -> this.modelMapper.map(cat1, CategoryDTO.class)) .collect(Collectors.toList());
		return catDTOs;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow( 
										()-> new ResourseNotFoundException("category", "categoryId", categoryId)  );
		this.categoryRepo.delete(cat);

	}

}
