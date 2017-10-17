package com.mes.msgboard.json;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author djp
 *
 */
@ApiModel(value="DiscussionRequest",description="Request object for creating a new discussion")
public class DiscussionRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7872465252019384662L;
	@ApiModelProperty(dataType = "Object", required = true, name = "discussion", value = "discussion")
	@NotNull(groups=Default.class)
	private DiscussionData discussion;
	
	
	/**
	 * 
	 * @param discussion
	 */
	public DiscussionRequest(DiscussionData discussion) {
		super();
		this.discussion = discussion;
	}


	public DiscussionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DiscussionData getDiscussion() {
		return discussion;
	}


	public void setDiscussion(DiscussionData discussion) {
		this.discussion = discussion;
	}
	
	
}
