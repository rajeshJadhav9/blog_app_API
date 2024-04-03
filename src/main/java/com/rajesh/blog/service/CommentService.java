package com.rajesh.blog.service;

import com.rajesh.blog.dto.CommentDTO;

public interface CommentService {

	CommentDTO createComments(CommentDTO commentDTO, Integer postId);
	void deletedComment(Integer commentId);
	
	
	
}
