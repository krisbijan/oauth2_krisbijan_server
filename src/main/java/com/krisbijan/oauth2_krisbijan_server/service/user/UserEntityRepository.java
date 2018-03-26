package com.krisbijan.oauth2_krisbijan_server.service.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.krisbijan.oauth2_krisbijan_server.model.Appuser;
import com.krisbijan.oauth2_krisbijan_server.model.UserEntity;

@Repository
@Transactional
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{
	

	public UserEntity findByEmail(String email);
    
}