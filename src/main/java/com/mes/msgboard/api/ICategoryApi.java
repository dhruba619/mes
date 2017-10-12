package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.json.CategoryRequest;
import com.mes.msgboard.json.CategoryResponse;

public interface ICategoryApi {

	public ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest);
}
