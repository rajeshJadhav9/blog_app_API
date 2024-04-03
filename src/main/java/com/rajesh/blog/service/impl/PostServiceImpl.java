package com.rajesh.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rajesh.blog.dto.PostDTO;
import com.rajesh.blog.dto.PostResponse;
import com.rajesh.blog.entities.Category;
import com.rajesh.blog.entities.Post;
import com.rajesh.blog.entities.User;
import com.rajesh.blog.exception.ResourseNotFoundException;
import com.rajesh.blog.repositories.CategoryRepo;
import com.rajesh.blog.repositories.PostRepo;
import com.rajesh.blog.repositories.UserRepo;
import com.rajesh.blog.service.PostService;


@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	
	@Override
	public PostDTO createPost(PostDTO postDTO,Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(  ()-> new ResourseNotFoundException("User", "UseerId", userId) ); 
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow( ()-> new ResourseNotFoundException("Category", "CategoryId", categoryId) );   
		
		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDTO.class);
	}

	
	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post  = this.postRepo.findById(postId).orElseThrow( ()-> new ResourseNotFoundException("Post", "PostId", postId) );
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDTO.class);
	}
	

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow( ()-> new ResourseNotFoundException("Post", "PostId", postId));
		return this.modelMapper.map(post, PostDTO.class);
	}
	

//	@Override
//	public List<PostDTO> getAllPost() {
//		List<Post> allPosts = this.postRepo.findAll();
//		List<PostDTO> postDTOs = allPosts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList()  );
//		return postDTOs;
//	}

//pagination 
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post> allPosts = pagePost.getContent();
		List<PostDTO> postDTOs = allPosts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList()  );
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDTOs);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	} 
	
	
	
	
	@Override
	public void deletePost(Integer postId) {
		Post dPost = this.postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post", "PostId", postId)) ;
		this.postRepo.delete(dPost);
	}

	
	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("User", "UserId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDTO> postDTOs = posts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList()) ;
		return postDTOs;
	}

	
	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("Category", "CategoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDTO> postDTOs = posts.stream().map((post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}


	
	@Override
	public List<PostDTO> searchPost(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDTO> postDTOs = posts.stream().map( (post)-> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList()   );
		return postDTOs;
	}

}
