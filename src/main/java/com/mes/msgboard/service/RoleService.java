package com.mes.msgboard.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mes.msgboard.entity.Role;
import com.mes.msgboard.repository.IRoleRepository;

@Service
public class RoleService {

	@Autowired
	private IRoleRepository repo;

	public List<Role> getAllROles() {
		return StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());

	}
	
	public Role createOrUpdateRole(Role role) {
		return repo.save(role);
	}

	public Role getRoleById(Integer id) {
		return repo.findOne(id);
	}
}
