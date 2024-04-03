package com.rajesh.blog.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajesh.blog.dto.CommentDTO;
import com.rajesh.blog.entities.Comment;
import com.rajesh.blog.entities.Post;
import com.rajesh.blog.exception.ResourseNotFoundException;
import com.rajesh.blog.repositories.CommentRepo;
import com.rajesh.blog.repositories.PostRepo;
import com.rajesh.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDTO createComments(CommentDTO commentDTO, Integer postId) {
		
		
		Post post = this.postRepo.findById(postId).orElseThrow( ()-> new ResourseNotFoundException("Post", "post Id", postId)   );
		
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDTO.class);
		
	}

	@Override
	public void deletedComment(Integer commentId) {
		 Comment com = this.commentRepo.findById(commentId).orElseThrow(  ()-> new ResourseNotFoundException("comment", "comment Id", commentId)  );
		 this.commentRepo.delete(com);
	}

}
