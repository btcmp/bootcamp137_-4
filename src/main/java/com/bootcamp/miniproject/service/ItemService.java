package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemDao;
import com.bootcamp.miniproject.dao.ItemInventoryDao;
import com.bootcamp.miniproject.dao.ItemVariantDao;
import com.bootcamp.miniproject.model.Item;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.ItemVariant;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	ItemDao itemDao;
	
	@Autowired
	ItemVariantDao variantDao;
	
	@Autowired
	ItemInventoryDao inventoryDao;
	
	public List<Item> getAll() {
		return itemDao.getAll();
	}

	public void save(Item item) {
		List<ItemVariant> itemVariant = item.getItemVariant();
		item.setItemVariant(null);
		itemDao.save(item);
		
		ItemInventory inventory;
		for(ItemVariant ivar : itemVariant) {
			inventory = ivar.getItemInventory().get(0);
			ivar.setItemInventory(null);
			ivar.setItem(item);
			System.out.println(item.getId());
			variantDao.save(ivar);
			
			inventory.setItemVariant(ivar);
			inventory.setEndingQty(inventory.getBeginning());
			inventoryDao.save(inventory);
		}
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
