package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.PurchaseRequestHistory;

public interface PurchaseRequestHistoryDao {

	public void save(PurchaseRequestHistory prHist);

	public void update(PurchaseRequestHistory prHist);
	
	public List<PurchaseRequestHistory> getPRHistoryByPr(Long prId);
}
//