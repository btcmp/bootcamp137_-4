package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.PurchaseRequest;

public interface PurchaseRequestDao {
	public List<PurchaseRequest> getAll();
	public PurchaseRequest getOne(long id);
	public void save(PurchaseRequest pr);
	public void update(PurchaseRequest pr);
	public void delete(PurchaseRequest pr);
	
}
