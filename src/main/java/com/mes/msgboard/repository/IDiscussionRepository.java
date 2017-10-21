package com.mes.msgboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.mes.msgboard.entity.Discussion;

public interface IDiscussionRepository extends CrudRepository<Discussion, Integer> ,JpaSpecificationExecutor<Discussion>{

	
	public List<Discussion> findByCategoryIdId(Integer integer);
	
	

}
