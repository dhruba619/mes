package com.mes.msgboard.json;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author djp
 * Request object for creating a new category
 */
@ApiModel(value="CategoryRequest",description="Request object for creating a new category")
public class CategoryRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7078974452642116996L;
	
	@ApiModelProperty(dataType="string",required=true,name="name",value="name")
	@NotNull
	private String name;
	
	@ApiModelProperty(dataType="string",required=true,name="description",value="description")
	@NotNull
	private String description;
	
	@ApiModelProperty(dataType="string",required=false,name="urlCode",value="urlCode")
	private String urlCode;
	
	@ApiModelProperty(dataType="integer",required=false,name="parentCategoryId",value="parentCategoryId")
	private Integer parentCategoryId;
	
	@ApiModelProperty(dataType="boolean",required=false,name="allowDiscussions",value="allowDiscussions")
	private boolean allowDiscussions;
	
	@ApiModelProperty(dataType="boolean",required=false,name="archived",value="archived")
	private boolean archived;
	
	

	/**
	 * 
	 */
	public CategoryRequest() {
		
	}

	/**
	 * @param name
	 * @param description
	 * @param urlCode
	 * @param parentCategoryId
	 * @param allowDiscussions
	 * @param archived
	 */
	public CategoryRequest(String name, String description, String urlCode, Integer parentCategoryId,
			boolean allowDiscussions, boolean archived) {
		super();
		this.name = name;
		this.description = description;
		this.urlCode = urlCode;
		this.parentCategoryId = parentCategoryId;
		this.allowDiscussions = allowDiscussions;
		this.archived = archived;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public boolean isAllowDiscussions() {
		return allowDiscussions;
	}

	public void setAllowDiscussions(boolean allowDiscussions) {
		this.allowDiscussions = allowDiscussions;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
