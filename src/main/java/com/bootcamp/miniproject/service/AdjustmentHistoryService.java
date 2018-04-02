package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.AdjustmentHistoryDao;
import com.bootcamp.miniproject.model.AdjustmentHistory;

@Service
@Transactional
public class AdjustmentHistoryService {
	
	@Autowired
	AdjustmentHistoryDao adjustmentHistoryDao;
	
	public void save(AdjustmentHistory adjustmentHistory) {
		adjustmentHistoryDao.save(adjustmentHistory);
	}
	
	public List<AdjustmentHistory> selectAll(){
		return adjustmentHistoryDao.selectAll();
	}
	
	public AdjustmentHistory getOne(long id) {
		AdjustmentHistory adjustmentHistory = new AdjustmentHistory();
		adjustmentHistory.setId(id);
		return adjustmentHistoryDao.getOne(adjustmentHistory);
	}
	
	public void update(AdjustmentHistory adjustmentHistory) {
		adjustmentHistoryDao.update(adjustmentHistory);
	}
	
	public void delete(AdjustmentHistory adjustmentHistory) {
		adjustmentHistoryDao.delete(adjustmentHistory);
	}
	
	
}
