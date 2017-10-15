package com.mes.msgboard.entity;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(dataType = "string", required = false, name = "id", value = "id")
	private Integer id;
	
	@Column(name = "name")
	@ApiModelProperty(dataType = "string", required = true, name = "name", value = "name")
	@NotNull
	private String name;
	
	@Column(name = "email")
	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "email", value = "email")
	private String email;
	
	@Column(name = "password")
	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "password", value = "password")
	private String password;
	
	@Column(name = "use_name")
	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "userName", value = "userName")
	private String userName;
	
	@Column(name = "is_active")
	@ApiModelProperty(dataType = "boolean", required = false, name = "isActive", value = "isActive")
	private boolean isActive;
	
	@Column(name = "last_login")
	@ApiModelProperty(dataType = "string", required = false, name = "lastLogin", value = "lastLogin")
	private Timestamp lastLogin;
	
	@Column(name = "image_url")
	@ApiModelProperty(dataType = "string", required = false, name = "imageUrl", value = "imageUrl")
	private String imageUrl;
	
	@Column(name = "role")
	@NotNull
	@ApiModelProperty(dataType = "integer", required = true, name = "role", value = "role")
	private Integer role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public User(Integer id, String name, String email, String password, String userName, boolean isActive,
			Timestamp lastLogin, String imageUrl, Integer role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.isActive = isActive;
		this.lastLogin = lastLogin;
		this.imageUrl = imageUrl;
		this.role = role;
	}

	public User() {
		
	}
	
	
}
