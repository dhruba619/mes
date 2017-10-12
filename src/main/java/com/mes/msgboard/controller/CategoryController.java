package com.mes.msgboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mes.msgboard.api.ICategoryApi;
import com.mes.msgboard.json.CategoryRequest;
import com.mes.msgboard.json.CategoryResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/v1.0/categories")
public class CategoryController implements ICategoryApi {

	@Override
	@RequestMapping(path = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create category", tags = "category", response = CategoryResponse.class, code = 201)
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Created", response = CategoryResponse.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

}
