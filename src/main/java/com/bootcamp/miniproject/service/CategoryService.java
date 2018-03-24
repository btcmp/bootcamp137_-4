package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.CategoryDao;
import com.bootcamp.miniproject.model.Category;
//
@Service 
@Transactional
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	public void save(Category category) {
		categoryDao.save(category);
	}
	
	public List<Category> selectAll(){
		return categoryDao.selectAll();
	}
	
	public Category getOne(long id) {
		Category category = new Category();
		category.setId(id);
		return categoryDao.getOne(category);
	}
	
	public void update(Category category) {
		categoryDao.update(category);
	}
	
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	public List<Category> getCategoryBySearchName(String search) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryBySearchName(search);
	}

	public List<Category> selectStatusActive() {
		// TODO Auto-generated method stub
		return categoryDao.selectStatusActive();
	}
}
