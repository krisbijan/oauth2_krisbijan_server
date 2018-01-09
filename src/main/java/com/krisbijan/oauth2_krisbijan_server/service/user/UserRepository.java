package com.krisbijan.oauth2_krisbijan_server.service.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.krisbijan.oauth2_krisbijan_server.model.Appuser;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Appuser, Integer>{
	

    @Modifying
    @Query("update Appuser user set user.password = :password where user.name = :name")
    public int changeUserPassword(@Param("name") String name, @Param("password") String password);


	Appuser findByName(String name);
    
}
