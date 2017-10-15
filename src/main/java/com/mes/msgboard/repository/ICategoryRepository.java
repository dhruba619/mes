package com.mes.msgboard.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.mes.msgboard.entity.Category;

public interface ICategoryRepository extends CrudRepository<Category, Serializable>{

}
