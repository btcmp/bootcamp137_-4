package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.AdjustmentDetail;

public interface AdjustmentDetailDao {

	public void save(AdjustmentDetail adjustmentDetail);
	public List<AdjustmentDetail> selectAll();
	public AdjustmentDetail getOne(AdjustmentDetail adjustmentDetail);
	public void update(AdjustmentDetail adjustmentDetail);
	public void delete(AdjustmentDetail adjustmentDetail);
	public List<AdjustmentDetail> getAdjustmentDetailByAdjustmentId(long search);
	
}
