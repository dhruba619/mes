package com.mes.msgboard.json;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserRequest", description = "UserRequest object")
public class UserRequest implements Serializable {

	private static final long serialVersionUID = 7717932283375202304L;
	@ApiModelProperty(dataType = "Object", required = false, name = "users", value = "users", notes = "Must be sent only in PUT")
	private UserData user;

	@NotNull(groups=Default.class)
	public UserData getUser() {
		return user;
	}

	public void setUser(UserData user) {
		this.user = user;
	}

	public UserRequest(UserData users) {
		super();
		this.user = users;
	}

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
