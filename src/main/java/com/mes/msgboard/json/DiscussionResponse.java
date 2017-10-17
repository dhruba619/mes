package com.mes.msgboard.json;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author djp
 *
 */
@ApiModel(value="DiscussionResponse",description="Discussion response object")
public class DiscussionResponse implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7803750207773646497L;
	@ApiModelProperty(dataType = "List", required = true, name = "discussion", value = "discussion")
	private List<DiscussionData> discussion;

	public List<DiscussionData> getDiscussion() {
		return discussion;
	}

	public void setDiscussion(List<DiscussionData> discussion) {
		this.discussion = discussion;
	}

	public DiscussionResponse(List<DiscussionData> discussion) {
		super();
		this.discussion = discussion;
	}

	public DiscussionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
