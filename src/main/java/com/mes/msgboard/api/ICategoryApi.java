package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.json.CategoryRequest;
import com.mes.msgboard.json.CategoryResponse;

public interface ICategoryApi {

	public ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest)
			throws MESException;

	public ResponseEntity<CategoryResponse> getCategories() throws MESException;

	public ResponseEntity<CategoryResponse> updateCategory(CategoryRequest categoryRequest)
			throws MESException;

	public ResponseEntity<CategoryResponse> deleteCategory(String id) throws MESException;

	public ResponseEntity<CategoryResponse> getCategory(String id)
			throws NumberFormatException, MESException;
	
	public ResponseEntity<CategoryResponse> disbaleDiscussion(String id)
			throws NumberFormatException, MESException;
	
	public ResponseEntity<CategoryResponse> searchCategoryByName(String testQuery)
			throws NumberFormatException, MESException;

}
