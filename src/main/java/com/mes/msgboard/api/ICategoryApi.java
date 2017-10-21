package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.json.CategoryRequest;
import com.mes.msgboard.json.CategoryResponse;

public interface ICategoryApi {

	public ResponseEntity<CategoryResponse> createCategory(String authToken, CategoryRequest categoryRequest)
			throws MESException;

	public ResponseEntity<CategoryResponse> getCategories(String authToken) throws MESException;

	public ResponseEntity<CategoryResponse> updateCategory(String authToken, CategoryRequest categoryRequest)
			throws MESException;

	public ResponseEntity<CategoryResponse> deleteCategory(String authToken, String id) throws MESException;

	public ResponseEntity<CategoryResponse> getCategory(String authToken, String id)
			throws NumberFormatException, MESException;
	
	public ResponseEntity<CategoryResponse> disbaleDiscussion(String authToken, String id)
			throws NumberFormatException, MESException;
	
	public ResponseEntity<CategoryResponse> searchCategoryByName(String authToken, String testQuery)
			throws NumberFormatException, MESException;

}
