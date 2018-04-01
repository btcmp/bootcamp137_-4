package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.SupplierDao;
import com.bootcamp.miniproject.model.Supplier;

@Service
@Transactional
public class SupplierService {

	@Autowired
	SupplierDao supplierDao;
	
	public void save(Supplier supplier) {
		supplierDao.save(supplier);
	}
	
	public void delete(Supplier supplier) {
		supplierDao.delete(supplier);
	}
	
	public void update(Supplier supplier) {
		supplierDao.update(supplier);
	}
	
	public List<Supplier> selectAll(){
		return supplierDao.selectAll();
	}
	
	public void saveUpdate(Supplier supplier) {
		supplierDao.saveUpdate(supplier);
	}
	
	public Supplier getOne(long id) {
		Supplier supplier = new Supplier();
		supplier.setId(id);
		return supplierDao.getOne(supplier);
	}

	public List<Supplier> getSupplierBySearchName(String search) {
		// TODO Auto-generated method stub
		return supplierDao.getSupplierBySearchName(search);
	}
	
	public List<Supplier> selectStatusActive() {
		// TODO Auto-generated method stub
		return supplierDao.selectStatusActive();
	}
}
