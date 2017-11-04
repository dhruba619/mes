package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.enums.SearchType;
import com.mes.msgboard.json.DiscussionRequest;
import com.mes.msgboard.json.DiscussionResponse;

public interface IDiscussionAPI {
	public ResponseEntity<DiscussionResponse> createDiscussion(DiscussionRequest discussionRequest)
			throws MESException;

	public ResponseEntity<DiscussionResponse> getAllDiscussion() throws MESException;

	public ResponseEntity<DiscussionResponse> getAllDiscussionByCategory(String categoryId)
			throws MESException;

	public ResponseEntity<DiscussionResponse> editDiscussion(DiscussionRequest discussionRequest)
			throws MESException;

	public ResponseEntity<DiscussionResponse> searchDiscussion(SearchType filter, String textQuery)
			throws MESException;
}
