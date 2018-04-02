package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.AdjustmentDao;
import com.bootcamp.miniproject.model.Adjustment;

@Service
@Transactional
public class AdjustmentService {

	@Autowired
	AdjustmentDao adjustmentDao;
	
	public void save(Adjustment adjustment) {
		adjustmentDao.save(adjustment);
	}
	
	public List<Adjustment> selectAll(){
		return adjustmentDao.selectAll();
	}
	
	public Adjustment getOne(long id) {
		Adjustment adjustment = new Adjustment();
		adjustment.setId(id);
		return adjustmentDao.getOne(adjustment);
	}
	
	public void update(Adjustment adjustment) {
		adjustmentDao.update(adjustment);
	}

	public void delete(Adjustment adjustment) {
		adjustmentDao.delete(adjustment);
	}
}
