package com.mes.msgboard.json;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CommentRequest",description="Request object for creating a new comment")
public class CommentRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1262703006369194855L;
	@ApiModelProperty(dataType = "Object", required = true, name = "comment", value = "comment")
	@NotNull(groups=Default.class)
	private CommentData comment;

	public CommentRequest(CommentData commentData) {
		super();
		this.comment = commentData;
	}

	public CommentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentData getCommentData() {
		return comment;
	}

	public void setCommentData(CommentData commentData) {
		this.comment= commentData;
	}
	
	
}
