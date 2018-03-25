package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.SalesOrder;

public interface SalesOrderDao {

	public void save(SalesOrder salesOrder);
	
	public List<SalesOrder> selectAll();
	
	public SalesOrder getOne(SalesOrder salesOrder);
	
	public void update(SalesOrder salesOrder);
	
	public void saveUpdate(SalesOrder salesOrder);
	
	public void delete(SalesOrder salesOrder);
}
