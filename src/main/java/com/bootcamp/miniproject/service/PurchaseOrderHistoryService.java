package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.PurchaseOrderHistoryDao;
import com.bootcamp.miniproject.model.PurchaseOrderHistory;

@Service
@Transactional
public class PurchaseOrderHistoryService {
	@Autowired
	PurchaseOrderHistoryDao pohDao;

	public List<PurchaseOrderHistory> getPOHistoryByPo(Long poId) {
		// TODO Auto-generated method stub
		return pohDao.getByPOid(poId);
	}

}
