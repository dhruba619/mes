package com.mes.msgboard.service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.Category;
import com.mes.msgboard.entity.Discussion;
import com.mes.msgboard.entity.User;
import com.mes.msgboard.enums.SearchType;
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

	public List<Discussion> createDiscusion(DiscussionData data) throws MESException {

		List<Discussion> discussions = new ArrayList<>();

		Discussion discussion = new Discussion();
		Category cat;
		User user;
		try {
			cat = categoryService.getCategory(data.getCategoryId()).get(0);
			if (!cat.isAllowDiscussions()) {
				throw new MESException("LOCKED", "Category is locked for any further discussions",
						HttpStatus.INTERNAL_SERVER_ERROR, null);
			}

			categoryService.lockCatgeroryForUpdates(cat);

			user = userService.getUserFromToken();
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
		discussion.setTitle(data.getTitle());
		discussion.setType(data.getType());
		discussion.setCreatedOn(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));

		try {
			discussions.add(discussionRepository.save(discussion));
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return discussions;

	}

	public List<Discussion> getAllDiscusion() throws MESException {
		List<Discussion> discussions;
		try {
			discussions = StreamSupport.stream(discussionRepository.findAll().spliterator(), false)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return discussions;

	}

	public List<Discussion> getAllDiscusionByCategory(String categoryId) throws MESException {
		List<Discussion> discussions;
		try {
			discussions = discussionRepository.findByCategoryIdId(Integer.valueOf(categoryId));
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return discussions;
	}

	public List<Discussion> updateDiscussion(DiscussionData data) throws MESException {
		List<Discussion> discussions = new ArrayList<>();

		Discussion discussion = new Discussion();
		Category cat;
		User user;
		try {
			cat = categoryService.getCategory(data.getCategoryId()).get(0);
			user = userService.getUserFromToken();
		} catch (MESException e) {
			throw e;
		}
		discussion.setId(data.getId());
		discussion.setCategoryId(cat);
		discussion.setCreatedBy(user);
		discussion.setBody(data.getBody());
		discussion.setAnnounce(data.isAnnounce());
		discussion.setClosed(data.isClosed());
		discussion.setSink(data.isSink());
		discussion.setTags(data.getTags());
		discussion.setTitle(data.getTitle());
		discussion.setType(data.getType());
		discussion.setCreatedOn(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));

		try {
			discussions.add(discussionRepository.save(discussion));
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return discussions;
	}

	public List<Discussion> searchDiscussion(SearchType filter, String textQuery) throws MESException {
		List<Discussion> discussions;
		try {
			switch (filter) {
			case BODY:
				discussions = discussionRepository.findAll(new Specification<Discussion>() {

					@Override
					public Predicate toPredicate(Root<Discussion> root, CriteriaQuery<?> arg1,
							CriteriaBuilder builder) {
						return builder.like(builder.upper(root.<String>get("body")),
								"%" + textQuery.toUpperCase() + "%");
					}
				});
				break;
			case TITLE:
				discussions = discussionRepository.findAll(new Specification<Discussion>() {

					@Override
					public Predicate toPredicate(Root<Discussion> root, CriteriaQuery<?> arg1,
							CriteriaBuilder builder) {
						return builder.like(builder.upper(root.<String>get("title")),
								"%" + textQuery.toUpperCase() + "%");
					}
				});
				break;

			default:
				throw new MESException("SEARCH_ERROR", "Search filter must be either BODY or TITLE",
						HttpStatus.INTERNAL_SERVER_ERROR, null);

			}
		} catch (Exception e) {

			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return discussions;
	}

	/**
	 * 
	 * @param id
	 * @throws MESException
	 */
	public List<Discussion> getDiscussion(Integer id) throws MESException {
		Discussion dis = getCategoryById(id);
		if (null != dis) {
			List<Discussion> discussion = new ArrayList<>();
			discussion.add(dis);
			return discussion;
		} else {
			throw new MESException("NOT_FOUND", "Discussion not found", HttpStatus.NOT_FOUND, null);
		}
	}

	private Discussion getCategoryById(Integer id) {
		return discussionRepository.findOne(id);
	}
}
