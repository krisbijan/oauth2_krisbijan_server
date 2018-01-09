package com.krisbijan.oauth2_krisbijan_server.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	Set<Appuser> users;

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

	public void setName(String name) {
		this.name = name;
	}

	public Set<Appuser> getUsers() {
		return users;
	}

	public void setUsers(Set<Appuser> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return name;
	}
	
	public Role() {
		
	}

}
