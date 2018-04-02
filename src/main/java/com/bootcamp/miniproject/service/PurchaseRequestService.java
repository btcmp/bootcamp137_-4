package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.PurchaseRequestDao;
import com.bootcamp.miniproject.dao.PurchaseRequestDetailDao;
import com.bootcamp.miniproject.dao.PurchaseRequestHistoryDao;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.PurchaseRequestDetail;
import com.bootcamp.miniproject.model.PurchaseRequestHistory;

@Service
@Transactional
public class PurchaseRequestService {
	
	@Autowired
	PurchaseRequestDao prDao;
	
	@Autowired
	PurchaseRequestDetailDao prDetailDao;
	
	@Autowired
	PurchaseRequestHistoryDao prHistoryDao;
	
	public List<PurchaseRequest> getAll() {
		return prDao.getAll();
	}
	
	public PurchaseRequest getOne(long id) {
		PurchaseRequest pr = prDao.getOne(id);
		System.out.println("Berhasil buka");
		List<PurchaseRequestDetail> prd = prDetailDao.getPRDetailByPRIdandOutletId(id, pr.getOutlet().getId());
		//List<PurchaseRequestHistory> prh = prHistoryDao.getPRHistoryByPR(id);
		if (prd.isEmpty()) {
			
		} else {
			pr.setPurchaseRequestDetail(prd);
			System.out.println("berhasil set pr detail");
		}
		return pr;
	}

	public void save(PurchaseRequest pr) {
		System.out.println("Msuk Service");
		List<PurchaseRequestDetail> prDetail = pr.getPurchaseRequestDetail();
		pr.setPurchaseRequestDetail(null);
		prDao.save(pr);
		
		for(PurchaseRequestDetail prd : prDetail) {
			prd.setPurchaseRequest(pr);
			prDetailDao.save(prd);
		}
		PurchaseRequestHistory prh = new PurchaseRequestHistory();
		prh.setPurchaseRequest(pr);
		prh.setStatus(pr.getStatus());
		prh.setCreatedOn(pr.getCreatedOn());
		prHistoryDao.save(prh);
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

	public List<PurchaseRequestDetail> getPRDetailByPRIdandOutletId(Long prId, Long outletId) {
		return prDetailDao.getPRDetailByPRIdandOutletId(prId, outletId);
	}

	public List<PurchaseRequest> getAllPrByOutlet(Long outletId) {
		return prDao.getAllPrByOutlet(outletId);
	}
	
}
