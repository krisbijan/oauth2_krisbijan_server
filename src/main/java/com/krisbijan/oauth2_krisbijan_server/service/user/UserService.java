package com.krisbijan.oauth2_krisbijan_server.service.user;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.krisbijan.oauth2_krisbijan_server.Action;
import com.krisbijan.oauth2_krisbijan_server.model.Appuser;


@Service
public class UserService {
	private final Logger LOGGER = LoggerFactory.getLogger("appuser");

	@Autowired
	private static UserRepository repository;

	public ResponseEntity<Object> createUser(Appuser user, Action action) {
		Appuser savedUser = repository.save(user);
		LOGGER.debug(action + "User created: " + user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	public ResponseEntity<Object> changeUserPassword(Appuser user, Action action) {
		Appuser getUser = repository.findByName(user.getName());
		if (getUser == null) {
			LOGGER.error(action + "No user in DB: " + user);
			throw new com.krisbijan.oauth2_krisbijan_server.exception.UserNotFoundException("Username-" + user.getName());
		}
		repository.changeUserPassword(user.getName(), user.getPassword());
		LOGGER.debug(action + "Password changed: " + user);
		return null;
	}

	public Appuser getUser(String username, Action action) {
		Appuser getUser = repository.findByName(username);
		if (getUser == null) {
			LOGGER.error(action + "No user in DB: " + username);
			throw new com.krisbijan.oauth2_krisbijan_server.exception.UserNotFoundException("Username-" + username);
		}
		LOGGER.debug(action + "Got user: " + username);
		return getUser;
	}

	public Appuser deleteUser(String username, Action action) {
		Appuser getUser = repository.findByName(username);
		if (getUser == null) {
			LOGGER.error(action + "No user in DB: " + username);
			throw new com.krisbijan.oauth2_krisbijan_server.exception.UserNotFoundException("Username-" + username);
		}
		repository.delete(getUser);
		LOGGER.debug(action + "User deleted: " + username);
		return getUser;
	}

}
