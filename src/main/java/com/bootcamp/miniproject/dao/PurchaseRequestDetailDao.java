package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.PurchaseRequestDetail;

public interface PurchaseRequestDetailDao {
	
	public void save (PurchaseRequestDetail prDetail);
	public void update (PurchaseRequestDetail prDetail);
	public void delete (PurchaseRequestDetail prDetail);
	public List<PurchaseRequestDetail> getPRDetailByPRIdandOutletId(Long prId, Long outletId);
	public List<Object> findPRDetailAndQty(Long outletId, Long prId);
	public List<PurchaseRequestDetail> getDetailByPRId(long id);
	public PurchaseRequestDetail getOne(Long id);
}
//