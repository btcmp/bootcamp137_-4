package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.ItemInventory;

public interface ItemInventoryDao {

	public List<ItemInventory> getAll();

	public void save(ItemInventory itemInventory);

	public ItemInventory getOne(long id);

	public void delete(ItemInventory itemInventory);

	public void update(ItemInventory itemInventory);

	public List<ItemInventory> searchItemInventoryByItemNameAndOutlet(Long outletId,String search);
	public List<ItemInventory> searchItemInventoryByItemName(String search);

	public List<ItemInventory> getInventoryByItemId(long id);

	public List<ItemInventory> searchInventoryByVariantId(long variantId);

	public ItemInventory searchInventoryByVariantAndOutletId(Long id, Long outletId);

	public List<ItemInventory> getInvetoryByItemIdandOutletId(Long id, Long outletId);

	public List<ItemInventory> getInventoryOutletId(Long outletId);

	public List<ItemInventory> getInventoryByVariant(Long id);
}
