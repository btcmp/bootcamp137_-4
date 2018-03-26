package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.SalesOrderDetail;

public interface SalesOrderDetailDao {

	public void save(SalesOrderDetail salesOrderDetail);
	
	public List<SalesOrderDetail> selectAll();
	
	public SalesOrderDetail getOne(SalesOrderDetail salesOrderDetail);
	
	public void update(SalesOrderDetail salesOrderDetail);
	
	public void saveUpdate(SalesOrderDetail salesOrderDetail);
	
	public void delete(SalesOrderDetail salesOrderDetail);
}
