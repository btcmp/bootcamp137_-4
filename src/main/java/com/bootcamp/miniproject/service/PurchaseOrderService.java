package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemInventoryDao;
import com.bootcamp.miniproject.dao.PurchaseOrderDao;
import com.bootcamp.miniproject.dao.PurchaseOrderDetailDao;
import com.bootcamp.miniproject.dao.PurchaseOrderHistoryDao;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseOrderDetail;
import com.bootcamp.miniproject.model.PurchaseOrderHistory;

@Service
@Transactional
public class PurchaseOrderService {
	@Autowired
	PurchaseOrderDao poDao;
	
	@Autowired
	PurchaseOrderDetailDao podDao;
	
	@Autowired
	PurchaseOrderHistoryDao pohDao;
	
	@Autowired
	ItemInventoryDao invDao;
	
	public List<PurchaseOrder> getAll(){
		return poDao.getAll();
	}
	
	public PurchaseOrder getOne(Long id) {
		
		PurchaseOrder puchaseOrder = poDao.getOne(id);
//		List<PurchaseOrderDetail> podList = podDao.getDetailByPO(puchaseOrder);
//		List<PurchaseOrderHistory> pohList = pohDao.getByPO(puchaseOrder);
		return puchaseOrder;
	}
	
	public void update(PurchaseOrder purchaseOrder) {
		
	}
	
	public ItemInventory getInventoryByVariantAndOutletId(Long poId, Long podId){
		PurchaseOrder purchaseOrder = poDao.getOne(poId);
		PurchaseOrderDetail poDetail = podDao.getOne(podId);
		return invDao.searchInventoryByVariantAndOutletId(poDetail.getVariant().getId(), purchaseOrder.getOutlet().getId());
	}
	
	
}
