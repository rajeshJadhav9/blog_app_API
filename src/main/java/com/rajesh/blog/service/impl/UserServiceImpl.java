package com.rajesh.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajesh.blog.dto.UserDTO;
import com.rajesh.blog.entities.User;
import com.rajesh.blog.exception.ResourseNotFoundException;
import com.rajesh.blog.repositories.UserRepo;
import com.rajesh.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;

// add model mapper	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.dtoToUser(userDTO);
		User  savedUser = this.userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO upadateUser(UserDTO userDTO, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(
										()-> new ResourseNotFoundException("User","id",userId) );
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDTO userDTO1 = this.userToDTO(updatedUser);
		return userDTO1;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow( 
									()-> new ResourseNotFoundException("User", "id", userId));
		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> users = this.userRepo.findAll();
		List<UserDTO> userDTOs = users.stream().map( user -> this.userToDTO(user)).collect(Collectors.toList());
			
		return userDTOs;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(
								()-> new ResourseNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	
	}  

	
	
	// use model mapper instead of this methods 
	
//	private User dtoToUser (UserDTO userDTO) {
//		User user = new User();
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
//		return user;
//	}
	
	private User dtoToUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);
		return user;
	}
	
	
	
//	private UserDTO userToDTO(User user) {
//		UserDTO userDTO = new UserDTO();
//		userDTO.setId(user.getId());
//		userDTO.setName(user.getName());
//		userDTO.setEmail(user.getEmail());
//		userDTO.setPassword(user.getPassword());
//		userDTO.setAbout(user.getAbout());
//		return userDTO;
//	}
	
	private UserDTO userToDTO(User user) {
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	
	
	
	
	
	
	
	
	
}
