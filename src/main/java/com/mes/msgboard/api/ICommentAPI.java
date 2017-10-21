package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.json.CommentRequest;
import com.mes.msgboard.json.CommentResponse;

public interface ICommentAPI {

	public ResponseEntity<CommentResponse> createComment(String authToken, CommentRequest commentRequest) throws MESException;

	public ResponseEntity<CommentResponse> updateComment(String authToken, CommentRequest commentRequest) throws MESException;

	public ResponseEntity<CommentResponse> deleteComment(String authToken, String commentId) throws MESException;

	public ResponseEntity<CommentResponse> getCommentByDiscussion(String authToken, String discussionId) throws MESException;
}
