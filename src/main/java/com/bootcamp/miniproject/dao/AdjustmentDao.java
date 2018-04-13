package com.bootcamp.miniproject.dao;

import java.util.Date;
import java.util.List;

import com.bootcamp.miniproject.model.Adjustment;

public interface AdjustmentDao {

	public void save (Adjustment adjustment);
	public List<Adjustment> selectAll();
	public Adjustment getOne(Adjustment adjustment);
	public void update(Adjustment adjustment);
	public void delete(Adjustment adjustment);
	public List<Adjustment> getAdjustmentIdByOutletId(long outletId);
	public List<Adjustment> searchAdjustmentByDateRange(Date start, Date end);
	
}
