package com.rajesh.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rajesh.blog.config.AppConstants;
import com.rajesh.blog.dto.APIResponse;
import com.rajesh.blog.dto.PostDTO;
import com.rajesh.blog.dto.PostResponse;
import com.rajesh.blog.service.FileService;
import com.rajesh.blog.service.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
//@Validated
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	
	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, 
													@PathVariable Integer userId, 
													@PathVariable Integer categoryId){
		PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost (@RequestBody PostDTO postDTO,@PathVariable Integer postId){
		PostDTO updatedPostDTO = this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatedPostDTO, HttpStatus.OK);
	}
	
	// delete
	@DeleteMapping("/posts/{postId}")
	public APIResponse deletePost( @PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new APIResponse("Post successfully deleted !!", true);
	}
	 	
	//get by postId
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO postDTOs = this.postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDTOs, HttpStatus.OK);
	}
	
	// get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
		List<PostDTO> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
		
	}
	
	// get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDTO> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
		
	}
	
	
//	// get all 
//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDTO>> getAllPosts(){
//		List<PostDTO> allPosts = this.postService.getAllPost();
//		return new ResponseEntity<List<PostDTO>>(allPosts, HttpStatus.OK);
//		
//	}
	
	// get all 
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue= AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue= AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue= AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue= AppConstants.SORT_DIR,required = false) String sortDir
			){
		PostResponse allPosts = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
		
	}

	
// Search Post 
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(
			@PathVariable("keywords") String keywords){
		
		List<PostDTO> result = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);
				
	}
	
	
	
	
//	image upload in post
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage (
			@RequestParam ("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
	
		PostDTO postDTO = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);

		postDTO.setImageName(fileName);
		PostDTO updatedPost = this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatedPost , HttpStatus.OK);
	}
	
	
//method to serve file
	
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage (
			@PathVariable ("imageName") String imageName,
			HttpServletResponse response
			)throws IOException {
		
			InputStream resource = this.fileService.getResourse(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource, response.getOutputStream());
				
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
