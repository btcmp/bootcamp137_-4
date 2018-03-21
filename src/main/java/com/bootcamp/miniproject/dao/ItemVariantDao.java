package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.ItemVariant;

public interface ItemVariantDao {

	List<ItemVariant> getAll();

	ItemVariant getOne(long id);

	void save(ItemVariant itemVariant);

	List<ItemVariant> getItemVariantBySearchName(String search);

	void delete(ItemVariant itemVariant);

	void update(ItemVariant itemVariant);

}
