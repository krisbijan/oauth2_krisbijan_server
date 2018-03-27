package com.krisbijan.oauth2_krisbijan_server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Role implements GrantedAuthority{


	
	@Id
	@JsonIgnore
	private Integer id;
	
	@Size(min=1)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<UserEntity> users = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Role(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public void addUser(UserEntity user) {
		this.users.add(user);
		user.getRoles().add(this);
	}

	public void removeUser(UserEntity user) {
		this.users.remove(user);
		user.getRoles().remove(this);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return name;
	}
	
	public Role() {
		
	}

}
