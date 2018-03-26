package com.krisbijan.oauth2_krisbijan_server.service.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.krisbijan.oauth2_krisbijan_server.model.Appuser;
import com.krisbijan.oauth2_krisbijan_server.model.UserEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Appuser, Integer>{
	

    @Modifying
    @Query("update Appuser user set user.password = :password where user.email = :email")
    public Appuser changeUserPassword(@Param("email") String email, @Param("password") String password);

	public Appuser findByEmail(String email);
    
}
