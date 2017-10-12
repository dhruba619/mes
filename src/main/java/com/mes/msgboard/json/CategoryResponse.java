package com.mes.msgboard.json;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author djp
 *
 */
@ApiModel(value="CategoryResponse",description="Category response object")
public class CategoryResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6405493752691274767L;
	
	@ApiModelProperty(dataType="integer",required=true,name="id",value="id")
	@NotNull
	private Integer id;
	
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
	
	@ApiModelProperty(dataType="integer",required=false,name="createdBy",value="createdBy")
	private Integer createdBy;
	
	@ApiModelProperty(dataType="date-time",required=false,name="createdOn",value="createdOn")
	private ZonedDateTime createdOn;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param urlCode
	 * @param parentCategoryId
	 * @param allowDiscussions
	 * @param archived
	 * @param createdBy
	 * @param createdOn
	 */
	public CategoryResponse(Integer id, String name, String description, String urlCode, Integer parentCategoryId,
			boolean allowDiscussions, boolean archived, Integer createdBy, ZonedDateTime createdOn) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.urlCode = urlCode;
		this.parentCategoryId = parentCategoryId;
		this.allowDiscussions = allowDiscussions;
		this.archived = archived;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
