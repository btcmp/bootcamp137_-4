package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.PurchaseRequestDao;
import com.bootcamp.miniproject.dao.PurchaseRequestDetailDao;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.PurchaseRequestDetail;

@Service
@Transactional
public class PurchaseRequestService {
	
	@Autowired
	PurchaseRequestDao prDao;
	
	@Autowired
	PurchaseRequestDetailDao prDetailDao;
	
	public List<PurchaseRequest> getAll() {
		return prDao.getAll();
	}
	
	public PurchaseRequest getOne(long id) {
		return prDao.getOne(id);
	}

	public void save(PurchaseRequest pr) {
		List<PurchaseRequestDetail> prDetail = pr.getPurchaseRequestDetail();
		pr.setPurchaseRequestDetail(null);
		prDao.save(pr);
		
		for(PurchaseRequestDetail prd : prDetail) {
			prd.setPurchaseRequest(pr);
			prDetailDao.save(prd);
		}
	}

	public void update(PurchaseRequest pr) {
		List<PurchaseRequestDetail> prDetail = pr.getPurchaseRequestDetail();
		pr.setPurchaseRequestDetail(null);
		prDao.update(pr);
		
		for(PurchaseRequestDetail prd : prDetail) {
			prd.setPurchaseRequest(pr);
			if (prd.getId() == null) {
				prDetailDao.save(prd);
			} else {
				prDetailDao.update(prd);
			}
		}
		
	}

	public void delete(PurchaseRequest pr) {
		prDao.delete(pr);
	}
	
}
