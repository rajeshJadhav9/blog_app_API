package com.rajesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajesh.blog.dto.CategoryDTO;
import com.rajesh.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	

}
