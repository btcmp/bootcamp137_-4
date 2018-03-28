package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.RoleDao;
import com.bootcamp.miniproject.model.Role;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleDao roleDao;
	
	public void save(Role role) {
		roleDao.save(role);
	}
	
	public List<Role> selectAll(){
		return roleDao.selectAll();
	}
	
	public Role getOne(long id) {
		Role role = new Role();
		role.setId(id);
		return roleDao.getOne(role);
	}
	
	public void update(Role role) {
		roleDao.update(role);
	}
	
	public void delete(Role role) {
		roleDao.delete(role);
	}
}
