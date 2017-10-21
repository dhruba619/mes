package com.mes.msgboard.json;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="CommentResponse",description="Comment response object")
public class CommentResponse implements Serializable{

	@ApiModelProperty(dataType = "List", required = true, name = "comment", value = "comment")
	private List<CommentData> comment;

	public List<CommentData> getCommentData() {
		return comment;
	}

	public void setCommentData(List<CommentData> commentData) {
		this.comment = commentData;
	}

	public CommentResponse(List<CommentData> commentData) {
		super();
		this.comment = commentData;
	}

	public CommentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
