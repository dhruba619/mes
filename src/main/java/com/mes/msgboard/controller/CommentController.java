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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mes.msgboard.api.ICommentAPI;
import com.mes.msgboard.common.CommentResponseMapper;
import com.mes.msgboard.common.DiscussionResponseMapper;
import com.mes.msgboard.common.MESException;
import com.mes.msgboard.json.CategoryResponse;
import com.mes.msgboard.json.CommentRequest;
import com.mes.msgboard.json.CommentResponse;
import com.mes.msgboard.json.ErrorResponse;
import com.mes.msgboard.service.CommentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/v1.0")
public class CommentController implements ICommentAPI {

	@Autowired
	CommentService commentService;

	@Autowired
	Validator validator;

	@Override
	@RequestMapping(path = "/comments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create comment", tags = "comment", response = CommentResponse.class, code = 201)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = CategoryResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<CommentResponse> createComment(@RequestHeader(name = "Authorization") String authToken,
			@RequestBody CommentRequest commentRequest) throws MESException {
		try {
			if (validator.validate(commentRequest, Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			if (validator.validate(commentRequest.getCommentData(), Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new CommentResponseMapper()
					.apply(commentService.createComment(authToken, commentRequest.getCommentData())));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "/comments", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update comment", tags = "comment", response = CommentResponse.class, code = 202)
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Created", response = CategoryResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<CommentResponse> updateComment(@RequestHeader(name = "Authorization") String authToken,
			@RequestBody CommentRequest commentRequest) throws MESException {
		try {

			if (commentRequest.getCommentData().getId() == null) {
				throw new MESException("BAD_REQUEST", "ID must be present for update", HttpStatus.BAD_REQUEST, null);
			}
			if (validator.validate(commentRequest, Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			if (validator.validate(commentRequest.getCommentData(), Default.class).size() > 0) {
				throw new MESException("BAD_REQUEST", "Missing required param", HttpStatus.BAD_REQUEST, null);
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CommentResponseMapper()
					.apply(commentService.createComment(authToken, commentRequest.getCommentData())));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

	@Override
	@RequestMapping(path = "/comments/{commentId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete comment", tags = "comment", response = CommentResponse.class, code = 204)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<CommentResponse> deleteComment(@RequestHeader(name = "Authorization") String authToken,
			@ApiParam(name = "commentId", required = true) @PathVariable(name = "commentId", required = true) String commentId)
			throws MESException {
		try {
			commentService.deleteComment(Integer.valueOf(commentId));
		} catch (Exception e) {
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Override
	@RequestMapping(path = "/discussions/{discussionId}/comments", method = RequestMethod.GET)
	@ApiOperation(value = "Get all comments by discussion", tags = "comment", response = CommentResponse.class, code = 204)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
			@ApiResponse(code = 401, message = "UnAuthorized", response = ErrorResponse.class) })
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<CommentResponse> getCommentByDiscussion(
			@RequestHeader(name = "Authorization") String authToken,
			@ApiParam(name = "discussionId", required = true) @PathVariable(name = "discussionId", required = true) String discussionId)
			throws MESException {
		try {

			return ResponseEntity.status(HttpStatus.OK).body(new CommentResponseMapper()
					.apply(commentService.getAllCommentsByDiscussion(Integer.valueOf(discussionId))));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR,
					e);
		}
	}

}
