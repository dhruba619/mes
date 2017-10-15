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

import io.swagger.annotations.ApiModelProperty;

public class CategoryData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2764166438917640142L;
	
	@ApiModelProperty(dataType = "string", required = false, name = "id", value = "id")
	@JsonProperty(required=false)
	public Integer id;

	@ApiModelProperty(dataType = "string", required = true, name = "name", value = "name")
	@NotNull(groups=Default.class)
	@NotBlank(groups=Default.class)
	@NotEmpty(groups=Default.class)
	@JsonProperty(required=true)
	private String name;

	@ApiModelProperty(dataType = "string", required = true, name = "description", value = "description")
	@NotNull(groups=Default.class)
	@NotBlank(groups=Default.class)
	@NotEmpty(groups=Default.class)
	@JsonProperty(required=true)
	private String description;

	@ApiModelProperty(dataType = "string", required = false, name = "urlCode", value = "urlCode")
	@JsonProperty(required=false)
	private String urlCode;

	@ApiModelProperty(dataType = "integer", required = false, name = "parentCategoryId", value = "parentCategoryId")
	@JsonProperty(required=false)
	private Integer parentCategoryId;

	@ApiModelProperty(dataType = "boolean", required = false, name = "allowDiscussions", value = "allowDiscussions")
	@JsonProperty(required=false)
	private boolean allowDiscussions;

	@ApiModelProperty(dataType = "boolean", required = false, name = "archived", value = "archived")
	@JsonProperty(required=false)
	private boolean archived;

	@ApiModelProperty(dataType = "integer", required = false, name = "createdBy", value = "createdBy")
	@JsonProperty(required=false)
	private Integer createdBy;

	@ApiModelProperty(dataType = "date-time", required = false, name = "createdOn", value = "createdOn",example="2017-10-15T09:27:10.000+0000")
	@JsonProperty(required=false)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime createdOn;

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

	public CategoryData(Integer id, String name, String description, String urlCode, Integer parentCategoryId,
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

	public CategoryData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
