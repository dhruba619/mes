package com.mes.msgboard.json;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel(description="User response object")
public class UserResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<UserData> users;

	public List<UserData> getUsers() {
		return users;
	}

	public void setUsers(List<UserData> users) {
		this.users = users;
	}

	public UserResponse(List<UserData> users) {
		super();
		this.users = users;
	}

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
