package com.mes.msgboard.common;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mes.msgboard.entity.Discussion;
import com.mes.msgboard.json.DiscussionData;
import com.mes.msgboard.json.DiscussionResponse;

public class DiscussionResponseMapper implements Function<List<Discussion>, DiscussionResponse> {

	@Override
	public DiscussionResponse apply(List<Discussion> discussions) {

		DiscussionResponse response = new DiscussionResponse();

		response.setDiscussion(
				discussions.stream().map(this::mapDiscussionToDiscussionData).collect(Collectors.toList()));
		return response;
	}

	private DiscussionData mapDiscussionToDiscussionData(Discussion discussion) {
		return new DiscussionData(discussion.getId(), discussion.getBody(), discussion.getFormat(),
				discussion.getCategoryId().getId(), discussion.getTags(), discussion.getType(), discussion.isClosed(),
				discussion.isAnnounce(), discussion.isSink(), discussion.getCreatedBy().getId(),
				ZonedDateTime.of(discussion.getCreatedOn().toLocalDateTime(), ZoneId.systemDefault()));
	}
}
