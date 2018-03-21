package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Supplier;

public interface SupplierDao {

	public void save(Supplier supplier);
	
	public List<Supplier> selectAll();
	
	public Supplier getOne(Supplier supplier);
	
	public void update(Supplier supplier);
	
	public void saveUpdate(Supplier supplier);
	
	public void delete(Supplier supplier);
	
	public List<Supplier> getSupplierBySearchName(String search);
}
