package com.mes.msgboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "role")
public class Role {
  
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(dataType = "string", required = false, name = "id", value = "id")
	private Integer id;

	@Column(name = "value", unique=true)
	@ApiModelProperty(dataType = "value", required = true, name = "value", value = "value")
	@NotNull
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Role(Integer id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
