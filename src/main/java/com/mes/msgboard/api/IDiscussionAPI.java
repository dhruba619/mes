package com.mes.msgboard.api;

import org.springframework.http.ResponseEntity;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.json.DiscussionRequest;
import com.mes.msgboard.json.DiscussionResponse;

public interface IDiscussionAPI {
	public ResponseEntity<DiscussionResponse> createDiscussion(String authToken,DiscussionRequest discussionRequest) throws MESException;
}
