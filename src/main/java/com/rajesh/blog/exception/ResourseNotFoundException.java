package com.rajesh.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourseNotFoundException extends RuntimeException{

	private String resourseName;
	private String fieldName;
	private long fieldValue;
	
	
	public ResourseNotFoundException (String resoureName, String fielName, long fieldValue) {
		super (String.format("%s not found with %s : %s", resoureName, fielName,fieldValue));
		this.resourseName= resoureName;
		this.fieldName = fielName;
		this.fieldValue = fieldValue;
		
	}
	
}
