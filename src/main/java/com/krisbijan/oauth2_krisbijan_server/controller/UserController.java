package com.krisbijan.oauth2_krisbijan_server.controller;

import org.hibernate.mapping.Set;
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

import com.krisbijan.oauth2_krisbijan_server.model.Appuser;
import com.krisbijan.oauth2_krisbijan_server.model.UserEntity;
import com.krisbijan.oauth2_krisbijan_server.service.user.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Appuser> registerNew(@RequestBody Appuser user, @RequestParam String password) {
		user.setPassword(password);
		user = userService.registerNew(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PostMapping("/changePWD")
	public ResponseEntity<Appuser> changePWD(@RequestParam String password, Authentication authentication) {
		Appuser user = userService.changePWD(password, authentication.getName());
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Appuser> getOne(@PathVariable Integer id) {
		Appuser user = userService.getOne(id);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

}