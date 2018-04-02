package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemInventoryDao;
import com.bootcamp.miniproject.dao.ItemVariantDao;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.ItemVariant;

@Service
@Transactional
public class ItemVariantService {
	
	@Autowired
	ItemVariantDao itemVariantDao;
	
	@Autowired
	ItemInventoryDao inventoryDao;
	
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
	public List<ItemVariant> getVariantByItemId(Long id){
		return itemVariantDao.getVariantByItemId(id);
	}
	
	// Integrasi Outlet Login
	public List<ItemVariant> getAllVariant(Long outletId){
		List<ItemVariant> varList = itemVariantDao.getAll();
		for (ItemVariant var:varList) {
			ItemInventory iInv = inventoryDao.searchInventoryByVariantAndOutletId(var.getId(), outletId);
			var.setItemInventory(null);
		}
		return varList;
	}


	public void deleteVar(long id) {
		ItemVariant var = itemVariantDao.getOne(id);
		var.setActive(false);
		itemVariantDao.update(var);
	}
	// Unused Methods
	public void delete(ItemVariant itemVariant) {
		itemVariantDao.delete(itemVariant);
	}

	public void update(ItemVariant itemVariant) {
		itemVariantDao.update(itemVariant);
	}
}
