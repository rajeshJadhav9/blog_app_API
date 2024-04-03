package com.rajesh.blog.service;

import java.util.List;

import com.rajesh.blog.dto.PostDTO;
import com.rajesh.blog.dto.PostResponse;
import com.rajesh.blog.entities.Post;

public interface PostService {

	// create update get getAll delete
	
	PostDTO createPost (PostDTO postDTO,Integer userId, Integer categoryId);
	PostDTO updatePost (PostDTO postDTO, Integer postId);
	void deletePost (Integer postId);
	PostDTO getPostById (Integer postId);
//	List<PostDTO> getAllPost();
//pagination 
	//List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize);
	//PostResponse getAllPost(Integer pageNumber, Integer pageSize);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
	// find post by user and category
	
	List<PostDTO> getPostByUser(Integer userId);
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	
	List<PostDTO> searchPost(String keyword);					// searching method
}
