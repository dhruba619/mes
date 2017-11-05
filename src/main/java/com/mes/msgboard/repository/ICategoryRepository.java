package com.mes.msgboard.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mes.msgboard.entity.Category;

public interface ICategoryRepository extends CrudRepository<Category, Serializable>,JpaSpecificationExecutor<Category>{

	List<Category> findByNameLike(String textQuery);
	
	List<Category> findAllByPurged(boolean purged);
	
	@Modifying
	@Transactional
	@Query("update Category cat set cat.purged = :status where cat.id = :id")
	int purge(@Param("status") boolean status, @Param("id") Integer id);

}
