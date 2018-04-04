package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.PurchaseRequestHistoryDao;
import com.bootcamp.miniproject.model.PurchaseRequestHistory;

@Service
@Transactional
public class PurchaseRequestHistoryService {
	
	@Autowired
	PurchaseRequestHistoryDao prHistDao;
	
	public void save(PurchaseRequestHistory prHist) {
		prHistDao.save(prHist);
	}
	public void update(PurchaseRequestHistory prHist) {
		prHistDao.update(prHist);
	}
	public List<PurchaseRequestHistory> getPRHistoryByPr(Long prId){
		return prHistDao.getPRHistoryByPr(prId);
	}
	
}
