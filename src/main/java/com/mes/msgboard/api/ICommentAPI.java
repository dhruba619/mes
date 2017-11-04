package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.json.CommentRequest;
import com.mes.msgboard.json.CommentResponse;

public interface ICommentAPI {

	public ResponseEntity<CommentResponse> createComment(CommentRequest commentRequest) throws MESException;

	public ResponseEntity<CommentResponse> updateComment(CommentRequest commentRequest) throws MESException;

	public ResponseEntity<CommentResponse> deleteComment(String commentId) throws MESException;

	public ResponseEntity<CommentResponse> getCommentByDiscussion(String discussionId) throws MESException;
}
