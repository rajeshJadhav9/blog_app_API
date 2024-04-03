package com.rajesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajesh.blog.entities.Comment;

public interface CommentRepo extends JpaRepository <Comment, Integer>   {

}
