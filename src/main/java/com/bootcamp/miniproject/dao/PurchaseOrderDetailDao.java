package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseOrderDetail;

public interface PurchaseOrderDetailDao {
	public void save(PurchaseOrderDetail poDetail);
	
	public void update(PurchaseOrderDetail poDetail);
	
	public void delete(PurchaseOrderDetail poDetail);
	
	public List<PurchaseOrderDetail> getAll();
	
	public PurchaseOrderDetail getOne(long id);

	public List<PurchaseOrderDetail> getDetailByPO(PurchaseOrder po);
}
