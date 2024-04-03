package com.rajesh.blog.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.rajesh.blog.entities.Comment;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	private CategoryDTO category;
	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>(); 
}
