package com.mes.msgboard.controller;

import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mes.msgboard.api.IUserApi;
import com.mes.msgboard.common.MESException;
import com.mes.msgboard.common.UserResponseMapper;
import com.mes.msgboard.json.ErrorResponse;
import com.mes.msgboard.json.UserRequest;
import com.mes.msgboard.json.UserResponse;
import com.mes.msgboard.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author djp
 *
 */
@RestController
@RequestMapping(path = "/api/${mes.api.version}/users")
public class UserController implements IUserApi {

	@Autowired
	Validator validator;

	@Autowired
	UserService userService;

	@Override
	@ApiOperation(value = "Create user", tags = "user", response = UserResponse.class, code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class)})
	@ApiImplicitParam(paramType="header",name="Authorization",required=true)
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(path = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) throws MESException {

		try {
			if (validator.validate(request, Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			if (validator.validate(request.getUser(), Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new UserResponseMapper().apply(userService.saveUser(request.getUser())));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	@ApiOperation(value = "Get all user", tags = "user", response = UserResponse.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class)})
	@ApiImplicitParam(paramType="header",name="Authorization",required=true)
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getAllUser() {
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseMapper().apply(userService.getAllUser()));
	}

	@Override
	@ApiOperation(value = "update user", tags = "user", response = UserResponse.class, code = 202)
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = UserResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class)})
	@ApiImplicitParam(paramType="header",name="Authorization",required=true)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@RequestMapping(path = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request) throws MESException {
		try {
			if (validator.validate(request, Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			if (validator.validate(request.getUser(), Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new UserResponseMapper().apply(userService.saveUser(request.getUser())));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	@ApiOperation(value = "Get user", tags = "user", response = UserResponse.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class)})
	@ApiImplicitParam(paramType="header",name="Authorization",required=true)
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(path = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getUser(
			@ApiParam(name = "userId", required = true) @PathVariable("userId") String id) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new UserResponseMapper().apply(userService.getUser(Integer.valueOf(id))));
	}

}
