package com.rajesh.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.blog.dto.APIResponse;
import com.rajesh.blog.dto.CommentDTO;
import com.rajesh.blog.entities.Comment;
import com.rajesh.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(
			@RequestBody CommentDTO comment,
			@PathVariable Integer postId ){
		
		CommentDTO createdComments = this.commentService.createComments(comment, postId);
		return new ResponseEntity<CommentDTO>(createdComments,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<APIResponse> deleteComment(
			@PathVariable Integer commentId	){
		
		 this.commentService.deletedComment(commentId);
		return new ResponseEntity<APIResponse>(new APIResponse("Comment deleted successfully!!", true), HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
