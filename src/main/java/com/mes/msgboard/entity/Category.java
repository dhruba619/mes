package com.mes.msgboard.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
}
