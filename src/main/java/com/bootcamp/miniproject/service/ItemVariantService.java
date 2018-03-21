package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemVariantDao;
import com.bootcamp.miniproject.model.ItemVariant;

@Service
@Transactional
public class ItemVariantService {
	
	@Autowired
	ItemVariantDao itemVariantDao;
	
	public List<ItemVariant> getAll() {
		return itemVariantDao.getAll();
	}

	public void save(ItemVariant itemVariant) {
		itemVariantDao.save(itemVariant);
	}

	public ItemVariant getOne(long id) {
		return itemVariantDao.getOne(id);
	}

	public List<ItemVariant> getItemVariantBySearchName(String search) {
		return itemVariantDao.getItemVariantBySearchName(search);
	}

	public void delete(ItemVariant itemVariant) {
		itemVariantDao.delete(itemVariant);
	}

	public void update(ItemVariant itemVariant) {
		itemVariantDao.update(itemVariant);
	}
	
}
