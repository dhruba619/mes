package com.mes.msgboard.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mes.msgboard.common.MESException;
import com.mes.msgboard.entity.Category;
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
	public List<Category> createCategory(String authToken, CategoryData categoryData) throws MESException {

		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setAllowDiscussions(categoryData.isAllowDiscussions());
		category.setArchived(categoryData.isArchived());
		category.setCreatedBy(userService.getUserFromToken(authToken));
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
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return categories;
	}

	/**
	 * 
	 * @return
	 */
	public List<Category> getAllCategories() {
		return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	/**
	 * 
	 * @param id
	 * @throws MESException 
	 */
	public List<Category> getCategory(Integer id) throws MESException {
		Category cat = getCategoryById(id);
		if(null!=cat) {
			List<Category> category = new ArrayList<>();
			category.add(cat);
			return category;
		}else {
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
	public List<Category> updateCategory(String authToken, CategoryData categoryData) throws MESException {
		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setId(categoryData.getId());
		category.setAllowDiscussions(categoryData.isAllowDiscussions());
		category.setArchived(categoryData.isArchived());
		category.setCreatedBy(userService.getUserFromToken(authToken));
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
			throw new MESException("somecode", "error", HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return categories;
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteCategory(Integer id) {
		categoryRepository.delete(id);
		
	}

}
