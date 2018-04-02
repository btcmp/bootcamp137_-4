package com.bootcamp.miniproject.dao;

import java.sql.Date;
import java.util.List;

import com.bootcamp.miniproject.model.PurchaseOrder;

public interface PurchaseOrderDao {
	public void save(PurchaseOrder po);	
	
	public void update(PurchaseOrder po);
	
	public void delete(PurchaseOrder po);
	
	public List<PurchaseOrder> getAll();
	
	public PurchaseOrder getOne(long id);
	
	public void changeStatus(String status, long id);
	
	public List<PurchaseOrder> searchPO(String search);
	
	public List<PurchaseOrder> searchPOByDate(Date startDate, Date endDate);
	
	public List<PurchaseOrder> searchPOByStatus(String search);
}
