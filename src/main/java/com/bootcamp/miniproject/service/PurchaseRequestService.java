package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.PurchaseRequestDao;
import com.bootcamp.miniproject.model.PurchaseRequest;

@Service
@Transactional
public class PurchaseRequestService {
	
	@Autowired
	PurchaseRequestDao prDao;
	
	public List<PurchaseRequest> getAll() {
		return prDao.getAll();
	}
	
	public PurchaseRequest getOne(long id) {
		return prDao.getOne(id);
	}

	public void save(PurchaseRequest pr) {
		prDao.save(pr);
	}

	public void update(PurchaseRequest pr) {
		prDao.update(pr);
	}

	public void delete(PurchaseRequest pr) {
		prDao.delete(pr);
	}
	
}
