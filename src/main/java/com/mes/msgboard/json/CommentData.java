package com.mes.msgboard.json;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mes.msgboard.entity.User;

import io.swagger.annotations.ApiModelProperty;

public class CommentData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7304051364141214042L;

	@ApiModelProperty(dataType = "string", required = false, name = "id", value = "id")
	@JsonProperty(required=false)
	private Integer id;
	
	@ApiModelProperty(dataType = "string", required = true, name = "body", value = "body")
	@JsonProperty(required=true)
	@NotNull(groups=Default.class)
	@NotBlank(groups=Default.class)
	@NotEmpty(groups=Default.class)
	private String body;
	
	@ApiModelProperty(dataType = "integer", required = true, name = "discussionId", value = "discussionId")
	@JsonProperty(required=true)
	@NotNull(groups=Default.class)
	private Integer discussionId;
	
	@ApiModelProperty(readOnly=true, dataType = "integer", required = false, name = "createdBy", value = "createdBy")
	@JsonProperty(required=false)
	private Integer createdBy;
	
	@ApiModelProperty(readOnly=true,dataType = "date-time", required = false, name = "createdOn", value = "createdOn",example="2017-10-15T09:27:10.000+0000")
	@JsonProperty(required=false)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime createdOn;

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

	public Integer getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(Integer discussionId) {
		this.discussionId = discussionId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public ZonedDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(ZonedDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public CommentData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentData(Integer id, String body, Integer discussionId, Integer createdBy, ZonedDateTime createdOn) {
		super();
		this.id = id;
		this.body = body;
		this.discussionId = discussionId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	
	
}
