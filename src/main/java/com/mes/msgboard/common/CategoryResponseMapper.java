package com.mes.msgboard.common;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mes.msgboard.entity.Category;
import com.mes.msgboard.json.CategoryData;
import com.mes.msgboard.json.CategoryResponse;

public class CategoryResponseMapper implements Function<List<Category>, CategoryResponse> {

	@Override
	public CategoryResponse apply(List<Category> t) {

		CategoryResponse response = new CategoryResponse();
		response.setCategory(t.stream().map(this::mapCategoryToCategoryData).collect(Collectors.toList()));
		return response;

	}

	private CategoryData mapCategoryToCategoryData(Category category) {

		Integer parentCategoryId = (category.getParentCategoryId() != null ? category.getParentCategoryId().getId()
				: null);
		CategoryData data = new CategoryData(category.getId(), category.getName(), category.getDescription(),
				category.getUrlCode(), parentCategoryId, category.isAllowDiscussions(), category.isArchived(),
				category.getCreatedBy().getId(),
				ZonedDateTime.of(category.getCreatedOn().toLocalDateTime(), ZoneId.systemDefault()));
		return data;

	}

}
