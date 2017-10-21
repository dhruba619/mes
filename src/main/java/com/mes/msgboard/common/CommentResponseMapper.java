package com.mes.msgboard.common;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mes.msgboard.entity.Comment;
import com.mes.msgboard.entity.Discussion;
import com.mes.msgboard.json.CommentData;
import com.mes.msgboard.json.CommentResponse;
import com.mes.msgboard.json.DiscussionResponse;

public class CommentResponseMapper implements Function<List<Comment>, CommentResponse> {

	@Override
	public CommentResponse apply(List<Comment> t) {
		CommentResponse response = new CommentResponse();
		response.setCommentData(t.stream().map(this::convert).collect(Collectors.toList()));
		return response;
	}

	private CommentData convert(Comment comment) {
		return new CommentData(comment.getId(), comment.getBody(), comment.getDiscussionId().getId(),
				comment.getCreatedBy().getId(),
				ZonedDateTime.of(comment.getCreatedOn().toLocalDateTime(), ZoneId.systemDefault()));
	}

}
