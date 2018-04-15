package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.PurchaseOrderDetailDao;
import com.bootcamp.miniproject.model.PurchaseOrderDetail;

@Service
@Transactional
public class PurchaseOrderDetailService {
	
	@Autowired
	PurchaseOrderDetailDao podDao;
//	public List<PurchaseOrderDetail> findPODetailAndQty(Long outletId, Long poId) {
//		return podDao.getPODetailByPOIdandOutletId(poId, outletId);
//	}
	public List<Object> findPODetailAndQty(Long outletId, Long poId){
		return podDao.findPODetailAndQty(outletId, poId);
	}
	public List<PurchaseOrderDetail> getDetailByPOId(long id) {
		return podDao.getDetailByPOId(id);
	}


}
