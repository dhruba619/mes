package com.mes.msgboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mes.msgboard.entity.Comment;

public interface ICommentRepository extends CrudRepository<Comment, Integer> {

	List<Comment> findByDiscussionIdId(Integer discussionId);

}
