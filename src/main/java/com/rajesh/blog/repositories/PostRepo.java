package com.rajesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajesh.blog.entities.Category;
import com.rajesh.blog.entities.Post;
import com.rajesh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	// to find posts by user and category
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	// to find posts of title containing
	List<Post> findByTitleContaining ( String title);
	
	
}
