package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.User;

public interface UserDao {

	public void save(User user);
	public List<User> selectAll();
	public User getOne(User user);
	public void update(User user);
	public void delete(User user);
}
