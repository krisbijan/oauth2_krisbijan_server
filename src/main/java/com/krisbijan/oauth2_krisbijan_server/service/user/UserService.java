package com.krisbijan.oauth2_krisbijan_server.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krisbijan.oauth2_krisbijan_server.model.Appuser;
import com.krisbijan.oauth2_krisbijan_server.model.UserEntity;

@Service
public class UserService {
	private final Logger LOGGER = LoggerFactory.getLogger("appuser");

	@Autowired
	private static UserRepository repository;

	public Appuser registerNew(Appuser user) {
		return repository.save(user);
	}

	public Appuser changePWD(String password, String email) {
		return repository.changeUserPassword(email, password);
	}

	public Appuser getOne(Integer id) {
		return repository.findOne(id);
	}

}
