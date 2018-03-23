package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.ItemInventory;

public interface ItemInventoryDao {

	public List<ItemInventory> getAll();

	public void save(ItemInventory itemInventory);

	public ItemInventory getOne(long id);

	public void delete(ItemInventory itemInventory);

	public void update(ItemInventory itemInventory);

	public List<ItemInventory> searchItemInventoryByItemName(String search);
//
}
