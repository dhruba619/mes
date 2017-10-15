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
@ApiModel(value="CategoryResponse",description="Category response object")
public class CategoryResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1741222404104603188L;
	
	@ApiModelProperty(dataType = "List", required = true, name = "category", value = "category")
	private List<CategoryData> category;
	
	public List<CategoryData> getCategory() {
		return category;
	}
	public void setCategory(List<CategoryData> category) {
		this.category = category;
	}
}
