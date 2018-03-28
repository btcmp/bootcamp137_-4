package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Role;

public interface RoleDao {

	public void save(Role role);
	public List<Role> selectAll();
	public Role getOne(Role role);
	public void update(Role role);
	public void delete(Role role);
}
