package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemInventoryDao;
import com.bootcamp.miniproject.dao.SalesOrderDao;
import com.bootcamp.miniproject.dao.SalesOrderDetailDao;
import com.bootcamp.miniproject.model.ItemInventory;
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
	
	@Autowired
	ItemInventoryDao itemInventoryDao;
	
	public void save(SalesOrder salesOrder) {
		//salesOrderDao.save(salesOrder);
		
		SalesOrder so = new SalesOrder();
		so.setCustomer(salesOrder.getCustomer());
		so.setGrandTotal(salesOrder.getGrandTotal());
		so.setCreatedBy(salesOrder.getCreatedBy());
		so.setModifiedBy(salesOrder.getModifiedBy());
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
			salesOD.setCreatedBy(sod.getCreatedBy());
			salesOD.setModifiedBy(sod.getModifiedBy());
			salesOD.setItemInventory(sod.getItemInventory());
			salesOrderDetailDao.save(salesOD);
		}
		
		List<SalesOrderDetail> salesOrderDetail = salesOrder.getSalesOrderDetail();
		for(SalesOrderDetail SOD : salesOrderDetail) {
			ItemInventory invent = itemInventoryDao.getOne(SOD.getItemInventory().getId());
			invent.setEndingQty(invent.getEndingQty()-SOD.getQty());
			invent.setSalesOrderQty(invent.getSalesOrderQty()+SOD.getQty());
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

	public void updateStock(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		List<SalesOrderDetail> salesOrderDetail = salesOrder.getSalesOrderDetail();
		for(SalesOrderDetail SOD : salesOrderDetail) {
			ItemInventory invent = itemInventoryDao.getOne(SOD.getItemInventory().getId());
			invent.setEndingQty(invent.getEndingQty()-SOD.getQty());
			invent.setSalesOrderQty(invent.getSalesOrderQty()+SOD.getQty());
		}
	}
}
