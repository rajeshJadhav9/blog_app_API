package com.rajesh.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.blog.dto.APIResponse;
import com.rajesh.blog.dto.CategoryDTO;
import com.rajesh.blog.entities.Category;
import com.rajesh.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
@Validated
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory( @Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO createdCategory = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>( createdCategory, HttpStatus.CREATED);	
	}
	
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(  @Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
	CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO, categoryId);
	return ResponseEntity.ok(updatedCategory);
	
     }
	
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<APIResponse> deleteCategory(@Valid @PathVariable Integer categoryId ){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<APIResponse>( new APIResponse("Category deleted successfully ", true ), HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@Valid @PathVariable Integer categoryId){
		CategoryDTO categoryDTO = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDTO> (categoryDTO, HttpStatus.OK);
	}
	
	@GetMapping ("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
		List<CategoryDTO> allCategory = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDTO>> (allCategory,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
}