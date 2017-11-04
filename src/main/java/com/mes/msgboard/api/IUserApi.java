package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.json.UserRequest;
import com.mes.msgboard.json.UserResponse;

public interface IUserApi {
	public ResponseEntity<UserResponse> createUser(UserRequest user) throws MESException;
	
	public ResponseEntity<UserResponse> getAllUser();
	public ResponseEntity<UserResponse> updateUser(UserRequest user) throws MESException;

	public ResponseEntity<UserResponse> getUser(String id);


}
