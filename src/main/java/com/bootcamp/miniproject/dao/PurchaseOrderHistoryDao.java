package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseOrderHistory;

public interface PurchaseOrderHistoryDao {
	public void save(PurchaseOrderHistory poHist);
	
	public void update(PurchaseOrderHistory poHist);
	
	public void delete(PurchaseOrderHistory poHist);
	
	public List<PurchaseOrderHistory> getAll();
	
	public PurchaseOrderHistory getOne(PurchaseOrderHistory poHist);
	
	public void changeStatus(String status, long id);

	public List<PurchaseOrderHistory> selectByPO(PurchaseOrder po);
}
//