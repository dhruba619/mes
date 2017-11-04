package com.mes.msgboard.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.Role;

public interface IRoleApi {
	public ResponseEntity<Role> addRole(Role requestResponse) throws MESException;
	public ResponseEntity<List<Role>> getAllRoles();
}
