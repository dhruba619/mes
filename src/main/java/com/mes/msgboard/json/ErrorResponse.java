package com.mes.msgboard.json;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ErrorResponse",description="Error response object")
public class ErrorResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4538245163742442168L;

	@ApiModelProperty(dataType="string", name="code")
	private String code;
	
	@ApiModelProperty(dataType="string", name="description")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ErrorResponse(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	
	public ErrorResponse() {
		
	}
	
	

}
