package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.UserDao;
import com.bootcamp.miniproject.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;
	
	public void save(User user) {
		userDao.save(user);
	}
	
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}
	
	public List<User> selectAll(){
		return userDao.selectAll();
	}
	
	public User getOne(long id) {
		User user = new User();
		user.setId(id);
		return userDao.getOne(user);
	}
	
	public void update(User user) {
		userDao.update(user);
	}
	
	public void delete(User user) {
		userDao.delete(user);
	}
}
