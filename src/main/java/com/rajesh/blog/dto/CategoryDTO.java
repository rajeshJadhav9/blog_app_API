package com.rajesh.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

	private Integer categoryId;
	
	@NotBlank
	@Size(min= 3 , message= "min size of category title is 3")
	private String categoryTitle;
	
	@NotBlank
	@Size(min= 10 , message= "min size of category description is 10")
	private String categoryDescription;
	
	
}
