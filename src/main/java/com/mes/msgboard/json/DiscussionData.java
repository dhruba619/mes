package com.mes.msgboard.json;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author djp
 *
 */
public class DiscussionData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5101161275455306663L;

	@ApiModelProperty(dataType = "string", required = false, name = "id", value = "id")
	@JsonProperty(required=false)
	private Integer id;
	
	@ApiModelProperty(dataType = "string", required = true, name = "body", value = "body")
	@JsonProperty(required=true)
	private String body;
	
	@ApiModelProperty(dataType = "string", required = false, name = "format", value = "format")
	@JsonProperty(required=false)
	private String format;
	
	@ApiModelProperty(dataType = "string", required = true, name = "categoryId", value = "categoryId")
	@JsonProperty(required=true)
	private Integer categoryId;
	
	@ApiModelProperty(dataType = "string", required = false, name = "id", value = "id",notes="Comma seperated tags")
	@JsonProperty(required=false)
	private String tags;
	
	@ApiModelProperty(dataType = "string", required = false, name = "type", value = "type")
	@JsonProperty(required=false)
	private String type;
	
	@ApiModelProperty(dataType = "boolean", required = false, name = "closed", value = "closed")
	@JsonProperty(required=false)
	private boolean closed;
	
	@ApiModelProperty(dataType = "boolean", required = false, name = "id", value = "id")
	@JsonProperty(required=false)
	private boolean announce;
	
	@ApiModelProperty(dataType = "boolean", required = false, name = "sink", value = "sink")
	@JsonProperty(required=false)
	private boolean sink;
	
	@ApiModelProperty(dataType = "integer", required = false, name = "createdBy", value = "createdBy")
	@JsonProperty(required=false)
	private Integer createdBy;
	
	@ApiModelProperty(dataType = "date-time", required = false, name = "createdOn", value = "createdOn",example="2017-10-15T09:27:10.000+0000")
	@JsonProperty(required=false)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime createdOn;

	/**
	 * 
	 * @param id
	 * @param body
	 * @param format
	 * @param categoryId
	 * @param tags
	 * @param type
	 * @param closed
	 * @param announce
	 * @param sink
	 * @param createdBy
	 * @param createdOn
	 */
	public DiscussionData(Integer id, String body, String format, Integer categoryId, String tags, String type,
			boolean closed, boolean announce, boolean sink, Integer createdBy, ZonedDateTime createdOn) {
		super();
		this.id = id;
		this.body = body;
		this.format = format;
		this.categoryId = categoryId;
		this.tags = tags;
		this.type = type;
		this.closed = closed;
		this.announce = announce;
		this.sink = sink;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public DiscussionData() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public boolean isAnnounce() {
		return announce;
	}

	public void setAnnounce(boolean announce) {
		this.announce = announce;
	}

	public boolean isSink() {
		return sink;
	}

	public void setSink(boolean sink) {
		this.sink = sink;
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
