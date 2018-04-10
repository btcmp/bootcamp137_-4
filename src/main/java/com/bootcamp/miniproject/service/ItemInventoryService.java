package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemInventoryDao;
import com.bootcamp.miniproject.model.ItemInventory;

@Service
@Transactional
public class ItemInventoryService {

	@Autowired
	ItemInventoryDao itemInventoryDao;
	
	public List<ItemInventory> getAll() {
		return itemInventoryDao.getAll();
	}

	public void save(ItemInventory itemInventory) {
		itemInventoryDao.save(itemInventory);
	}
//
	public ItemInventory getOne(long id) {
		return itemInventoryDao.getOne(id);
	}

	public void delete(ItemInventory itemInventory) {
		itemInventoryDao.delete(itemInventory);
	}

	public void update(ItemInventory itemInventory) {
		itemInventoryDao.update(itemInventory);
	}
	
	public List<ItemInventory> searchItemInventoryByItemName(String search) {
		return itemInventoryDao.searchItemInventoryByItemName(search);
	}

	public List<ItemInventory> getInventoryByItemId(long id) {
		return itemInventoryDao.getInventoryByItemId(id);
	}

	public List<ItemInventory> searchItemInventoryByItemNameAndOutlet(Long outletId, String search) {
		return itemInventoryDao.searchItemInventoryByItemNameAndOutlet(outletId, search);
	}
	
	public List<ItemInventory> searchInventoryByVariantId(long variantId){
		return itemInventoryDao.searchInventoryByVariantId(variantId);
	}

	public List<ItemInventory> getInventoryByItemIdandOutletId(Long id, Long outletId) {
		return itemInventoryDao.getInvetoryByItemIdandOutletId(id, outletId);
	}

	public List<ItemInventory> getInventoryandOutletId(Long outletId) {
		return itemInventoryDao.getInventoryOutletId(outletId);
	}

	public List<ItemInventory> searchInventoryByOutlet(String search, Long outletId) {
		return itemInventoryDao.searchItemInventoryByItemNameAndOutlet(outletId, search);
	}
	

}
