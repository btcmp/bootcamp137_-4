package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.SalesOrderDao;
import com.bootcamp.miniproject.dao.SalesOrderDetailDao;
import com.bootcamp.miniproject.model.ItemVariant;
import com.bootcamp.miniproject.model.SalesOrder;
import com.bootcamp.miniproject.model.SalesOrderDetail;

@Service
@Transactional
public class SalesOrderService {

	@Autowired
	SalesOrderDao salesOrderDao;
	
	@Autowired
	SalesOrderDetailDao salesOrderDetailDao;
	
	public void save(SalesOrder salesOrder) {
		//salesOrderDao.save(salesOrder);
		
		SalesOrder so = new SalesOrder();
		so.setCustomer(salesOrder.getCustomer());
		so.setGrandTotal(salesOrder.getGrandTotal());
		salesOrderDao.save(so);
		
		for(SalesOrderDetail sod : salesOrder.getSalesOrderDetail()) {
			ItemVariant itemVariant = new ItemVariant();
			itemVariant.setId(sod.getItemVariant().getId());
			SalesOrderDetail salesOD = new SalesOrderDetail();
			salesOD.setItemVariant(itemVariant);
			salesOD.setSalesOrder(so);
			salesOD.setQty(sod.getQty());
			salesOD.setSubTotal(sod.getSubTotal());
			salesOD.setUnitPrice(sod.getUnitPrice());
			salesOrderDetailDao.save(salesOD);
		}
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
