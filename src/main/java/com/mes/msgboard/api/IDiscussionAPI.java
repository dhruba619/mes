package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.enums.SearchType;
import com.mes.msgboard.json.DiscussionRequest;
import com.mes.msgboard.json.DiscussionResponse;

public interface IDiscussionAPI {
	public ResponseEntity<DiscussionResponse> createDiscussion(String authToken, DiscussionRequest discussionRequest)
			throws MESException;

	public ResponseEntity<DiscussionResponse> getAllDiscussion(String authToken) throws MESException;

	public ResponseEntity<DiscussionResponse> getAllDiscussionByCategory(String authToken, String categoryId)
			throws MESException;

	public ResponseEntity<DiscussionResponse> editDiscussion(String authToken, DiscussionRequest discussionRequest)
			throws MESException;

	public ResponseEntity<DiscussionResponse> searchDiscussion(String authToken, SearchType filter, String textQuery)
			throws MESException;
}
