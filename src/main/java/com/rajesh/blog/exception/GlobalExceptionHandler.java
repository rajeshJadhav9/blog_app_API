package com.rajesh.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rajesh.blog.dto.APIResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<APIResponse> resourseNotFoundException(ResourseNotFoundException ex){
		String message= ex.getMessage();
		APIResponse apiResponse = new APIResponse(message, false);
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<>();
	ex.getBindingResult().getAllErrors().forEach( (error)-> {
			String fieldName = ( (FieldError) error).getField();  
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
	}  );	
	
	return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
	
	}
	
	
	
	
	
	
}
