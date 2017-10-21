package com.mes.msgboard.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.mes.msgboard.entity.Category;

public interface ICategoryRepository extends CrudRepository<Category, Serializable>,JpaSpecificationExecutor<Category>{

	List<Category> findByNameLike(String textQuery);

}
