package com.bootcamp.miniproject.service;

import java.util.List;

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
	public List<Object> findPRDetailAndQty(Long outletId, Long prId) {
		// TODO Auto-generated method stub
		return prDetailDao.findPRDetailAndQty(outletId, prId);
	}
	public List<PurchaseRequestDetail> getDetailByPRId(long id) {
		return prDetailDao.getDetailByPRId(id);
	}	
}
