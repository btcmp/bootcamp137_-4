package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.ItemVariant;

public interface ItemVariantDao {

	public List<ItemVariant> getAll();

	public ItemVariant getOne(long id);

	public void save(ItemVariant itemVariant);

	public List<ItemVariant> getItemVariantBySearchName(String search);

	public void delete(ItemVariant itemVariant);

	public void update(ItemVariant itemVariant);

	public List<ItemVariant> getVariantByItemId(Long id);

}
