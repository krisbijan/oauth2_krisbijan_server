package com.krisbijan.oauth2_krisbijan_server.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity
public class Appuser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;

	@Column(unique = true)
	@Size(min = 6, message = "Name should have at least 6 characters")
	private String name;

	@Past
	@Column
	private Date DOB;

	@Column
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Appuser(Integer id, @Size(min = 6, message = "Name should have at least 6 characters") String name,
			@Past Date dOB, String password, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		DOB = dOB;
		this.password = password;
		this.roles = roles;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getPassword() {
		return password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + name + ", DOB=" + DOB + "]";
	}

	public Appuser() {

	}

	public Appuser(@Size(min = 6, message = "Name should have at least 6 characters") String username, @Past Date dOB,
			String password) {
		super();
		this.name = username;
		DOB = dOB;
		this.password = password;
	}

}
