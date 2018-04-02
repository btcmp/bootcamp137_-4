package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.AdjustmentHistory;

public interface AdjustmentHistoryDao {

	public void save(AdjustmentHistory adjustmentHistory);
	public List<AdjustmentHistory> selectAll();
	public AdjustmentHistory getOne(AdjustmentHistory adjustmentHistory);
	public void update(AdjustmentHistory adjustmentHistory);
	public void delete(AdjustmentHistory adjustmentHistory);
	
}
