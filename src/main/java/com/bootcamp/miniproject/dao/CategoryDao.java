package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Category;

public interface CategoryDao {
	
	public void save(Category category);
	public List<Category> selectAll();
	public Category getOne(Category category);
	public void update(Category category);
	public void delete(Category category);
	public List<Category> getCategoryBySearchName(String search);
	public List<Category> selectStatusActive();
}
//