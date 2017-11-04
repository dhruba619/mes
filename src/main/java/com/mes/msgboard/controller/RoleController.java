package com.mes.msgboard.controller;

import java.util.List;

import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mes.msgboard.api.IRoleApi;
import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.Role;
import com.mes.msgboard.json.ErrorResponse;
import com.mes.msgboard.service.RoleService;
import com.mes.msgboard.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "/api/${mes.api.version}/roles")
public class RoleController implements IRoleApi{
	
	@Autowired
	Validator validator;

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;

	@Override
	@ApiOperation(value = "Create role", tags = "role", response = Role.class, code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Role.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class)})
	@ApiImplicitParam(paramType="header",name="Authorization",required=true)
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(path = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> addRole(@RequestBody Role role) throws MESException {
		try {
			if (validator.validate(role, Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(roleService.createOrUpdateRole(role));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	@ApiOperation(value = "Get all role", tags = "role", response = Role.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Created", response = List.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class)})
	@ApiImplicitParam(paramType="header",name="Authorization",required=true)
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> getAllRoles() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(roleService.getAllROles());
	}

}
