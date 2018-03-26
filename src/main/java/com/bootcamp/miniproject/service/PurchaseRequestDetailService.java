package com.bootcamp.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.PurchaseRequestDetailDao;
import com.bootcamp.miniproject.model.PurchaseRequestDetail;

@Service
@Transactional
public class PurchaseRequestDetailService {
	
	@Autowired
	PurchaseRequestDetailDao prDetailDao;
	
	public void save(PurchaseRequestDetail prDetail) {
		prDetailDao.save(prDetail);
	}
	public void update(PurchaseRequestDetail prDetail) {
		prDetailDao.update(prDetail);
	}
	public void delete(PurchaseRequestDetail prDetail) {
		prDetailDao.delete(prDetail);
	}	
}
