package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.SalesOrderDao;
import com.bootcamp.miniproject.model.SalesOrder;

@Service
@Transactional
public class SalesOrderService {

	@Autowired
	SalesOrderDao salesOrderDao;
	
	public void save(SalesOrder salesOrder) {
		salesOrderDao.save(salesOrder);
	}
	
	public void delete(SalesOrder salesOrder) {
		salesOrderDao.delete(salesOrder);
	}
	
	public void update(SalesOrder salesOrder) {
		salesOrderDao.update(salesOrder);
	}
	
	public List<SalesOrder> selectAll(){
		return salesOrderDao.selectAll();
	}
	
	public void saveUpdate(SalesOrder salesOrder) {
		salesOrderDao.saveUpdate(salesOrder);
	}
	
	public SalesOrder getOne(long id) {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setId(id);
		return salesOrderDao.getOne(salesOrder);
	}
}
