package com.mes.msgboard.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="comment")
public class Comment {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "body")
	private String body;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="discussion_id")
	private Discussion discussionId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="created_by")
	private User createdBy;
	
	@Column(name = "created_on")
	private Timestamp createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Discussion getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(Discussion discussionId) {
		this.discussionId = discussionId;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * 
	 * @param id
	 * @param body
	 * @param discussionId
	 * @param createdBy
	 * @param createdOn
	 */
	public Comment(Integer id, String body, Discussion discussionId, User createdBy, Timestamp createdOn) {
		super();
		this.id = id;
		this.body = body;
		this.discussionId = discussionId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
