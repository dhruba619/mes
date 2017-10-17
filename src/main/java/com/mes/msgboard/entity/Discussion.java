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

/**
 * 
 * @author djp
 *
 */
@Entity
@Table(name="discussion")
public class Discussion {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "format")
	private String format;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category categoryId;
	
	@Column(name = "tags")
	private String tags;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "closed")
	private boolean closed;
	
	@Column(name = "announce")
	private boolean announce;
	
	@Column(name = "sink")
	private boolean sink;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="created_by")
	private User createdBy;
	
	@Column(name = "created_on")
	private Timestamp createdOn;

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
	public Discussion(Integer id, String body, String format, Category categoryId, String tags, String type,
			boolean closed, boolean announce, boolean sink, User createdBy, Timestamp createdOn) {
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

	public Discussion() {
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

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
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
	
	
	
	
}
