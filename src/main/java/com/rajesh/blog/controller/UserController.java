package com.rajesh.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.blog.dto.APIResponse;
import com.rajesh.blog.dto.UserDTO;
import com.rajesh.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

	// post -create user
	// put - update 
	// get- get
	// delete - delete
	
	
	//add service class 
	@Autowired
	private UserService userService;
	

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser( @Valid @RequestBody UserDTO userDTO){
		UserDTO createdUser = this.userService.createUser(userDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{userId}")
	// "userId" and pathvariable name are different so added ("userId" in pathvariable value )
	public ResponseEntity<UserDTO> updateUser( @Valid @RequestBody UserDTO userDTO, @PathVariable ("userId")Integer id){
		UserDTO updatedUser= this.userService.upadateUser(userDTO, id);
		return ResponseEntity.ok(updatedUser);
		//return new ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable Integer userId){
		// return type is void so directly delete user method
		this.userService.deleteUser(userId);
		//return new ResponseEntity<> (Map.of("message", "User Deleted successfully"), HttpStatus.OK);
		return new ResponseEntity<APIResponse>(new APIResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
				
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	
	
}
