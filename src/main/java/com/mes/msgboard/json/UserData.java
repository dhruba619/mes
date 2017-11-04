package com.mes.msgboard.json;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description="User model")
public class UserData implements Serializable{

	private static final long serialVersionUID = -8368344767531108973L;

	@ApiModelProperty(dataType = "string", required = false, name = "id", value = "id")
	private Integer id;

	@ApiModelProperty(dataType = "string", required = true, name = "name", value = "name")
	@NotNull
	private String name;

	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "email", value = "email")
	private String email;
	
	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "password", value = "password")
	private String password;

	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "userName", value = "userName")
	private String username;

	@ApiModelProperty(dataType = "boolean", required = false, name = "isActive", value = "isActive")
	private boolean isActive;

	@ApiModelProperty(dataType = "string", required = false, name = "imageUrl", value = "imageUrl")
	private String imageUrl;

	@ApiModelProperty(dataType = "string", required = true, name = "role", value = "role")
	private Integer role;

	public UserData(Integer id, String name, String email, String password, String username, boolean isActive,
			String imageUrl, Integer role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
		this.isActive = isActive;
		this.imageUrl = imageUrl;
		this.role = role;
	}

	public UserData() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
	
	

}
