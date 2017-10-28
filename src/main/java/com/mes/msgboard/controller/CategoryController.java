package com.mes.msgboard.controller;

import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mes.msgboard.api.ICategoryApi;
import com.mes.msgboard.common.CategoryResponseMapper;
import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.User;
import com.mes.msgboard.enums.SearchType;
import com.mes.msgboard.json.CategoryRequest;
import com.mes.msgboard.json.CategoryResponse;
import com.mes.msgboard.json.ErrorResponse;
import com.mes.msgboard.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/v1.0/categories")
public class CategoryController implements ICategoryApi {

	@Autowired
	CategoryService categoryService;

	@Autowired
	Validator validator;

	@Override
	@RequestMapping(path = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create category", tags = "category", response = CategoryResponse.class, code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = CategoryResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class)})
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<CategoryResponse> createCategory(@RequestHeader(name = "Authorization") String authToken,
			@RequestBody CategoryRequest categoryRequest) throws MESException {
		try {
			if (validator.validate(categoryRequest, Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			if(validator.validate(categoryRequest.getCategory(), Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new CategoryResponseMapper()
					.apply(categoryService.createCategory(authToken, categoryRequest.getCategory())));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			e.printStackTrace();
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get category by id", tags = "category", response = CategoryResponse.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = CategoryResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class)})
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<CategoryResponse> getCategory(@RequestHeader(name = "Authorization") String authToken,
			@ApiParam(name = "categoryId", required = true)@PathVariable("categoryId")String id) throws NumberFormatException, MESException {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new CategoryResponseMapper().apply(categoryService.getCategory(Integer.valueOf(id))));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all categories", tags = "category", response = CategoryResponse.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = CategoryResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<CategoryResponse> getCategories(@RequestHeader(name = "Authorization") String authToken)
			throws MESException {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new CategoryResponseMapper().apply(categoryService.getAllCategories()));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update category", tags = "category", response = CategoryResponse.class, code = 202)
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = CategoryResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<CategoryResponse> updateCategory(@RequestHeader(name = "Authorization") String authToken,
			@RequestBody CategoryRequest categoryRequest) throws MESException {
		try {
			if (validator.validate(categoryRequest, Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			if (categoryRequest.getCategory().getId() == null) {
				throw new MESException("BAD_REQUEST", "ID must be present for update", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CategoryResponseMapper()
					.apply(categoryService.updateCategory(authToken, categoryRequest.getCategory())));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "/{categoryId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete category by id", tags = "category", response = CategoryResponse.class, code = 204)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<CategoryResponse> deleteCategory(@RequestHeader(name = "Authorization") String authToken,
			@ApiParam(name = "categoryId", required = true) @PathVariable("categoryId") String id) throws MESException {
		try {
			categoryService.deleteCategory(Integer.valueOf(id));
		} catch (Exception e) {
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Override
	@RequestMapping(path = "/{categoryId}/disable-discussion", method = RequestMethod.PATCH)
	@ApiOperation(value = "Disbale discussions for a category", tags = "category", response = CategoryResponse.class, code = 204)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<CategoryResponse> disbaleDiscussion(@RequestHeader(name = "Authorization") String authToken,
			@ApiParam(name = "categoryId", required = true) @PathVariable("categoryId") String id)
			throws NumberFormatException, MESException {
		try {
			categoryService.disableDiscussions(Integer.valueOf(id));
		} catch (Exception e) {
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Override
	@RequestMapping(path = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Search categories by name", tags = "category", response = CategoryResponse.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = CategoryResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<CategoryResponse> searchCategoryByName(@RequestHeader(name = "Authorization")String authToken,
			@ApiParam(example="some text",value="textQuery")@RequestParam("textQuery") String textQuery)
			throws NumberFormatException, MESException {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new CategoryResponseMapper().apply(categoryService.searchByTitle(textQuery)));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

}
