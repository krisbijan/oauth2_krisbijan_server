package com.krisbijan.oauth2_krisbijan_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krisbijan.oauth2_krisbijan_server.model.GenericResponse;
import com.krisbijan.oauth2_krisbijan_server.model.UserEntity;
import com.krisbijan.oauth2_krisbijan_server.service.user.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserEntity> registerNew(@RequestBody UserEntity user, @RequestParam String password) {
		user.setPassword(password);
		user = userService.registerNew(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PostMapping("/changePWD")
	public ResponseEntity<GenericResponse> changePWD(@RequestParam String password, Authentication authentication) {
		Integer i = userService.changePWD(password, authentication.getName());
		
		GenericResponse response = new GenericResponse();
		response.setCode(i);
		if (i==1)
			response.setResponse("Success");
		else
			response.setResponse("Error");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/resetPWD")
	public ResponseEntity<UserEntity> changePWD(@RequestParam String email) {
		//PLACEHOLDER
		UserEntity user = userService.getOne(email);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

}