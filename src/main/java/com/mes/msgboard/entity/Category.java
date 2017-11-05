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
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;

	
	@Column(name = "name",unique=true)
	private String name;

	@Column(name = "description")
	private String description;

	
	@Column(name = "url_code")
	private String urlCode;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parent_category_id")
	private Category parentCategoryId;

	@Column(name = "allow_discussions")
	private boolean allowDiscussions;

	@Column(name = "locked")
	private boolean locked;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="created_by")
	private User createdBy;
	
	@Column(name = "purged")
	private boolean purged;


	@Column(name = "created_on")
	private Timestamp createdOn;

	/**
	 * 
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
	public Category(Integer id, String name, String description, String urlCode, Category parentCategoryId,
			boolean allowDiscussions, boolean archived, User createdBy, Timestamp createdOn) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.urlCode = urlCode;
		this.parentCategoryId = parentCategoryId;
		this.allowDiscussions = allowDiscussions;
		this.locked = archived;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}
	
	

	/**
	 * 
	 */
	public Category() {
	
	}


	public boolean isPurged() {
		return purged;
	}



	public void setPurged(boolean purged) {
		this.purged = purged;
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

	public Category getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Category parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public boolean isAllowDiscussions() {
		return allowDiscussions;
	}

	public void setAllowDiscussions(boolean allowDiscussions) {
		this.allowDiscussions = allowDiscussions;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean archived) {
		this.locked = archived;
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
