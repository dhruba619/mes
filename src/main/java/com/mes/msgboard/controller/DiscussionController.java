package com.mes.msgboard.controller;

import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mes.msgboard.api.IDiscussionAPI;
import com.mes.msgboard.common.DiscussionResponseMapper;
import com.mes.msgboard.common.MESException;
import com.mes.msgboard.enums.SearchType;
import com.mes.msgboard.json.DiscussionRequest;
import com.mes.msgboard.json.DiscussionResponse;
import com.mes.msgboard.json.ErrorResponse;
import com.mes.msgboard.service.DiscussionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/v1.0")
public class DiscussionController implements IDiscussionAPI {

	@Autowired
	Validator validator;

	@Autowired
	DiscussionService discussionService;

	@Override
	@RequestMapping(path = "/discussions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Discussion", tags = "discussion", response = DiscussionResponse.class, code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = DiscussionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<DiscussionResponse> createDiscussion(@RequestHeader(name = "Authorization") String authToken,
			@RequestBody DiscussionRequest discussionRequest) throws MESException {
		try {
			if (validator.validate(discussionRequest, Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			if (validator.validate(discussionRequest.getDiscussion(), Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new DiscussionResponseMapper()
					.apply(discussionService.createDiscusion(authToken, discussionRequest.getDiscussion())));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "/discussions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get All Discussions", tags = "discussion", response = DiscussionResponse.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = DiscussionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<DiscussionResponse> getAllDiscussion(@RequestHeader(name = "Authorization") String authToken)
			throws MESException {
		try {

			return ResponseEntity.status(HttpStatus.OK)
					.body(new DiscussionResponseMapper().apply(discussionService.getAllDiscusion(authToken)));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "/categories/{categoryId}/discussions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get All Discussions for a category", tags = "discussion", response = DiscussionResponse.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = DiscussionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<DiscussionResponse> getAllDiscussionByCategory(
			@RequestHeader(name = "Authorization") String authToken,
			@ApiParam(name = "categoryId", required = true) @PathVariable("categoryId") String categoryId)
			throws MESException {
		try {

			return ResponseEntity.status(HttpStatus.OK)
					.body(new DiscussionResponseMapper().apply(discussionService.getAllDiscusionByCategory(authToken,categoryId)));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "/discussions", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a discussion", tags = "discussion", response = DiscussionResponse.class, code = 202)
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted", response = DiscussionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<DiscussionResponse> editDiscussion(@RequestHeader(name = "Authorization")String authToken,@RequestBody DiscussionRequest discussionRequest)
			throws MESException {
		try {
			if (discussionRequest.getDiscussion().getId() == null) {
				throw new MESException("BAD_REQUEST", "ID must be present for update", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new DiscussionResponseMapper()
					.apply(discussionService.updateDiscussion(authToken, discussionRequest.getDiscussion())));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "/discussions/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Search discussions", tags = "discussion", response = DiscussionResponse.class, code = 200)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = DiscussionResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<DiscussionResponse> searchDiscussion(@RequestHeader(name = "Authorization")String authToken,@ApiParam(example="body",value="filter") @RequestParam("filter") SearchType filter,
			@ApiParam(example="some text",value="textQuery")@RequestParam("textQuery") String textQuery) throws MESException {
		try {

			return ResponseEntity.status(HttpStatus.OK).body(new DiscussionResponseMapper()
					.apply(discussionService.searchDiscussion(authToken, filter,textQuery)));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

}
