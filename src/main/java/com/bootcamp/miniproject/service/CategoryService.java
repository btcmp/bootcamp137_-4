package com.bootcamp.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.CategoryDao;
import com.bootcamp.miniproject.dao.ItemDao;
import com.bootcamp.miniproject.model.Category;
import com.bootcamp.miniproject.model.Item;
//
@Service 
@Transactional
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ItemDao itemDao;
	
	public void save(Category category) {
		categoryDao.save(category);
	}
	
	public List<Category> selectAll(){
		List<Category> categories = categoryDao.selectStatusActive();
		List<Category> cat = new ArrayList();
 		for(Category category : categories) {
			List<Item> item = itemDao.getItemByCategory(category);
			if (item == null) {
				category.setItemStock(0);
			} else
			category.setItemStock(item.size());
			cat.add(category);
			System.out.println(category.getName()+" item= "+ category.getItemStock());
		}
		return cat;
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
