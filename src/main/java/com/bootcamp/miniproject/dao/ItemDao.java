package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Category;
import com.bootcamp.miniproject.model.Item;

public interface ItemDao {

	List<Item> getAll();
	void save(Item item);
	void update(Item item);
	void delete(Item item);
	Item getOne(long id);
	List<Item> getItemBySearchName(String search);
	List<Item> getItemByCategory(Category category);
}
