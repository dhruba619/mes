package com.mes.msgboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.mes.msgboard.entity.User;

public interface IUserRepository extends CrudRepository<User, Integer>{
	User findOneByUsername(String username);
}
