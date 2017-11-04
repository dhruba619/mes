package com.mes.msgboard.service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.Comment;
import com.mes.msgboard.entity.Discussion;
import com.mes.msgboard.entity.User;
import com.mes.msgboard.json.CommentData;
import com.mes.msgboard.repository.ICommentRepository;


@Service
public class CommentService {

	@Autowired
	private ICommentRepository commentRepository;

	@Autowired
	private DiscussionService discussionService;

	@Autowired
	private UserService userService;

	public List<Comment> createComment(CommentData data) throws MESException {
		List<Comment> comments = new ArrayList<>();

		Comment comment = new Comment();
		Discussion dis;
		User user;
		try {
			dis = discussionService.getDiscussion(data.getDiscussionId()).get(0);
			user = userService.getUserFromToken();
		} catch (MESException e) {
			throw e;
		}
		comment.setBody(data.getBody());
		comment.setCreatedBy(user);
		comment.setDiscussionId(dis);
		comment.setCreatedOn(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));

		try {
			comments.add(commentRepository.save(comment));
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return comments;

	}

	public List<Comment> updateComment(CommentData data) throws MESException {
		List<Comment> comments = new ArrayList<>();

		Comment comment = new Comment();
		Discussion dis;
		User user;
		try {
			dis = discussionService.getDiscussion(data.getDiscussionId()).get(0);
			user = userService.getUserFromToken();
		} catch (MESException e) {
			throw e;
		}
		comment.setId(data.getId());
		comment.setBody(data.getBody());
		comment.setCreatedBy(user);
		comment.setDiscussionId(dis);
		comment.setCreatedOn(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));

		try {
			comments.add(commentRepository.save(comment));
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return comments;
	}

	public void deleteComment(Integer id) {
		commentRepository.delete(id);
	}

	public List<Comment> getAllCommentsByDiscussion(Integer discussionId) throws MESException {
		List<Comment> comments;
		try {
			comments = StreamSupport.stream(commentRepository.findByDiscussionIdId(discussionId).spliterator(), false)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return comments;
	}
}
