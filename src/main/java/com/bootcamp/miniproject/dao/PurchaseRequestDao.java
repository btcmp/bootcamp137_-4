package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.PurchaseRequest;

public interface PurchaseRequestDao {
	public List<PurchaseRequest> getAll();
	public PurchaseRequest getOne(long id);
	public void save(PurchaseRequest pr);
	public void update(PurchaseRequest pr);
	public void delete(PurchaseRequest pr);
	public List<PurchaseRequest> getAllPrByOutlet(Long outletId);
	public void approve(long id);
	public void reject(long id);
	public void createPo(long id);
	
	public List<PurchaseRequest> searchPR(String search);
	
	public List<PurchaseRequest> searchPRByStatus(Long outletId, String search);
	public int CountPRByMonth(int month, int year);
	public List<PurchaseRequest> searchPR(Long outletId, String search);
}
//