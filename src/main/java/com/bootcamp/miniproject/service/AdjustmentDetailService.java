package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.AdjustmentDetailDao;
import com.bootcamp.miniproject.model.AdjustmentDetail;

@Service
@Transactional
public class AdjustmentDetailService {

	@Autowired
	AdjustmentDetailDao adjustmentDetailDao;
	
	public void save(AdjustmentDetail adjustmentDetail) {
		adjustmentDetailDao.save(adjustmentDetail);
	}
	
	public List<AdjustmentDetail> selectAll(){
		return adjustmentDetailDao.selectAll();
	}
	
	public AdjustmentDetail getOne(long id) {
		AdjustmentDetail adjustmentDetail = new AdjustmentDetail();
		adjustmentDetail.setId(id);
		return adjustmentDetailDao.getOne(adjustmentDetail);
	}
	
	public void update(AdjustmentDetail adjustmentDetail) {
		adjustmentDetailDao.update(adjustmentDetail);
	}
	
	public void delete(AdjustmentDetail adjustmentDetail) {
		adjustmentDetailDao.delete(adjustmentDetail);
	}
}
