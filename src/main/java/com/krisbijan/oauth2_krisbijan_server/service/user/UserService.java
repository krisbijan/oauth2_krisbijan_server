package com.krisbijan.oauth2_krisbijan_server.service.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.hibernate.collection.internal.PersistentSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krisbijan.oauth2_krisbijan_server.model.Role;
import com.krisbijan.oauth2_krisbijan_server.model.UserEntity;

@Service
public class UserService {
	private final Logger LOGGER = LoggerFactory.getLogger("appuser");

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;

	public UserEntity registerNew(UserEntity user) {
		user = repository.save(user);
		Role role = roleRepository.findByName("ROLE_USER");
		role.addUser(user);
		roleRepository.save(role);
		return user;
	}

	public Integer changePWD(String password, String email) {
		return repository.changeUserPassword(email, password);
	}

	public UserEntity getOne(Integer id) {
		UserEntity user = repository.findOne(id);
		return user;
	}

	public List<UserEntity> getAll() {
		List<UserEntity> users = repository.findAll();
		return users;
	}

	public UserEntity getOne(String email) {
		UserEntity user = repository.findByEmail(email);
		return user;
	}

}
