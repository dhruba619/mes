package com.mes.msgboard.json;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.mes.msgboard.entity.Category;

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
	private static final long serialVersionUID = 7321034855847653385L;
	
	@ApiModelProperty(dataType = "Object", required = true, name = "category", value = "category")
	@NotNull(groups=Default.class)
	private CategoryData category;
	
	
	public CategoryData getCategory() {
		return category;
	}
	public void setCategory(CategoryData category) {
		this.category = category;
	}
	
	
}
