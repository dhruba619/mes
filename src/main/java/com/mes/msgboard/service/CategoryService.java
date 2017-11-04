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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.Category;
import com.mes.msgboard.entity.User;
import com.mes.msgboard.json.CategoryData;
import com.mes.msgboard.repository.ICategoryRepository;

@Service
public class CategoryService {

	@Autowired
	ICategoryRepository categoryRepository;

	@Autowired
	UserService userService;

	/**
	 * 
	 * @param authToken
	 * @param categoryData
	 * @return
	 * @throws MESException
	 */
	public List<Category> createCategory(CategoryData categoryData) throws MESException {
		
		User user =userService.getUserFromToken();		
		if (!user.getRole().getValue().equals("ADMIN")) {
			throw new MESException("FORBIDDEN", "User is forbidden to perform this operation", HttpStatus.FORBIDDEN, null);
		}
		
		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setAllowDiscussions(categoryData.isAllowDiscussions());
		category.setLocked(categoryData.isLocked());
		category.setCreatedBy(user);
		category.setCreatedOn(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
		category.setDescription(categoryData.getDescription());
		category.setName(categoryData.getName());
		if (null != categoryData.getParentCategoryId() && categoryData.getParentCategoryId()!=0) {
			Category parent = getCategoryById(categoryData.getParentCategoryId());
			category.setParentCategoryId(parent);
			lockCatgeroryForUpdates(parent);
		}

		if (null != categoryData.getUrlCode()) {
			category.setUrlCode(categoryData.getUrlCode());
		} else {
			category.setUrlCode(createUrlCode(categoryData.getName()));
		}

		try {
			categories.add(categoryRepository.save(category));
		} catch (Exception e) {
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return categories;
	}

	/**
	 * 
	 * @return
	 */
	public List<Category> getAllCategories() {
		return StreamSupport.stream(categoryRepository.findAllByPurged(false).spliterator(), false).collect(Collectors.toList());
	}

	/**
	 * 
	 * @param id
	 * @throws MESException
	 */
	public List<Category> getCategory(Integer id) throws MESException {
		Category cat = getCategoryById(id);
		if (null != cat) {
			List<Category> category = new ArrayList<>();
			category.add(cat);
			return category;
		} else {
			throw new MESException("NOT_FOUND", "Category not found", HttpStatus.NOT_FOUND, null);
		}
	}

	private Category getCategoryById(Integer id) {
		return categoryRepository.findOne(id);
	}

	private String createUrlCode(String categoryName) {
		return String.valueOf(categoryName.hashCode());
	}

	/**
	 * 
	 * @param authToken
	 * @param category
	 * @return
	 * @throws MESException
	 */
	public List<Category> updateCategory(CategoryData categoryData) throws MESException {
		isCategoryLocked(categoryData.getId());
		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setId(categoryData.getId());
		category.setAllowDiscussions(categoryData.isAllowDiscussions());
		category.setLocked(categoryData.isLocked());
		category.setCreatedBy(userService.getUserFromToken());
		category.setCreatedOn(Timestamp.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
		category.setDescription(categoryData.getDescription());
		category.setName(categoryData.getName());
		if (null != categoryData.getParentCategoryId()) {
			category.setParentCategoryId(getCategoryById(categoryData.getParentCategoryId()));
		}

		if (null != categoryData.getUrlCode()) {
			category.setUrlCode(categoryData.getUrlCode());
		} else {
			category.setUrlCode(createUrlCode(categoryData.getName()));
		}

		try {
			categories.add(categoryRepository.save(category));
		} catch (Exception e) {
			if (e instanceof MESException) {
				throw e;
			}
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return categories;
	}

	private void isCategoryLocked(Integer id) throws MESException {
		Category cat = categoryRepository.findOne(id);
		if (cat.isLocked()) {
			throw new MESException("LOCKED", "Category is Locked for any further edits",
					HttpStatus.INTERNAL_SERVER_ERROR, null);
		}

	}

	/**
	 * 
	 * @param id
	 */
	public void deleteCategory(Integer id) {
		categoryRepository.delete(id);

	}

	@Async("threadPoolTaskExecutor")
	public void lockCatgeroryForUpdates(Category category) {
		if (!category.isLocked()) {
			category.setLocked(true);
			categoryRepository.save(category);
		}

	}

	public void disableDiscussions(Integer id) {
		Category cat = getCategoryById(id);
		cat.setAllowDiscussions(false);
		categoryRepository.save(cat);

	}

	public List<Category> searchByTitle(String textQuery) {
		return categoryRepository.findAll(new Specification<Category>() {

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> arg1, CriteriaBuilder builder) {
				
				return builder.like(builder.upper(root.<String>get("name")), "%" + textQuery.toUpperCase() + "%");
			}
		});
	}

}
