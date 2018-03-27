package com.krisbijan.oauth2_krisbijan_server.service.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.krisbijan.oauth2_krisbijan_server.model.UserEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	

    @Modifying
    @Query("update UserEntity user set user.password = :password where user.email = :email")
    public Integer changeUserPassword(@Param("email") String email, @Param("password") String password);

	public UserEntity findByEmail(String email);
    
}
