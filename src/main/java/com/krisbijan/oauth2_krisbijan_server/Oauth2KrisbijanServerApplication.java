package com.krisbijan.oauth2_krisbijan_server;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Oauth2KrisbijanServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2KrisbijanServerApplication.class, args);
	}
	
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
