package com.mes.msgboard.common;

import org.springframework.http.HttpStatus;

public class MESException extends Exception {

	private String code;
	private String description;
	private HttpStatus status;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2824500625584776480L;

	public MESException(String code, String description, HttpStatus status, Throwable e) {
		this.status = status;
		this.code = code;
		if (null != e) {
			this.description = description + "-" + e.getMessage();
		} else {
			this.description = description;
		}

	}

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

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
