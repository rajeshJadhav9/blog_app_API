package com.rajesh.blog.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import jakarta.validation.constrains.Email;

@NoArgsConstructor
@Getter
@Setter
//@Data
public class UserDTO {
	
	private Integer id;
	
//	@NotEmpty
//	@Size(min=3, message="user name must be min of 3 characters")
	private String name;
	
//	@Email(message= "email id is not valid")
	private String email;
	
//	@NotEmpty
//	@Size(min=3, max=12, message= "Password must contain min 3 and max 12 chars")
	private String password;
	
//	@NotNull
	private String about;

}
