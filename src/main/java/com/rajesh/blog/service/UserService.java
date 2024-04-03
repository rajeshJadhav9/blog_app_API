package com.rajesh.blog.service;

import java.util.List;

import com.rajesh.blog.dto.UserDTO;
import com.rajesh.blog.entities.User;

public interface UserService {

	// create update getById getAll delete
	UserDTO createUser(UserDTO user);
	UserDTO upadateUser(UserDTO user, Integer userId);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUser();
	void deleteUser(Integer userId);
	
	
}
