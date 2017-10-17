package com.mes.msgboard.service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.Category;
import com.mes.msgboard.entity.Discussion;
import com.mes.msgboard.entity.User;
import com.mes.msgboard.json.DiscussionData;
import com.mes.msgboard.repository.IDiscussionRepository;

@Service
public class DiscussionService {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	IDiscussionRepository discussionRepository;
	
	public List<Discussion> createDiscusion(String authToken, DiscussionData data) throws MESException{
		
		List<Discussion> discussions = new ArrayList<>();
		
		Discussion discussion = new Discussion();
		Category cat;
		User user;
		try {
			cat = categoryService.getCategory(data.getCategoryId()).get(0);
			user= userService.getUserFromToken(authToken);
		} catch (MESException e) {
			throw e;
		}
		discussion.setCategoryId(cat);
		discussion.setCreatedBy(user);
		discussion.setBody(data.getBody());
		discussion.setAnnounce(data.isAnnounce());
		discussion.setClosed(data.isClosed());
		discussion.setSink(data.isSink());
		discussion.setTags(data.getTags());
		discussion.setFormat(data.getFormat());
		discussion.setType(data.getType());
		discussion.setCreatedOn(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
		
		try {
			discussions.add(discussionRepository.save(discussion));
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return discussions;
		
	}
}
