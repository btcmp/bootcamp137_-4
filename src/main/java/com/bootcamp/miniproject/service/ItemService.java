package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemDao;
import com.bootcamp.miniproject.model.Item;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	ItemDao itemDao;
	
	public List<Item> getAll() {
		return itemDao.getAll();
	}

	public void save(Item item) {
		itemDao.save(item);
	}

	public Item getOne(long id) {
		return itemDao.getOne(id);
	}

	public void update(Item item) {
		itemDao.update(item);
	}

	public void delete(Item item) {
		itemDao.delete(item);
	}

	public List<Item> getItemBySearchName(String search) {
		return itemDao.getItemBySearchName(search);
	}

	
}
