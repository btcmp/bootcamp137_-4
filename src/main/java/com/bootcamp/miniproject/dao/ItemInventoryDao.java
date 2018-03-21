package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.ItemInventory;

public interface ItemInventoryDao {

	List<ItemInventory> getAll();

	void save(ItemInventory itemInventory);

	ItemInventory getOne(long id);

	void delete(ItemInventory itemInventory);

	void update(ItemInventory itemInventory);

}
