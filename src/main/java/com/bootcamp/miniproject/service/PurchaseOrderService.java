package com.bootcamp.miniproject.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.PurchaseRequestDetail;
import com.bootcamp.miniproject.model.PurchaseRequestHistory;
import com.bootcamp.miniproject.model.User;

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
	
	@Autowired
	HttpSession httpSession;
	
	public List<PurchaseOrder> getAll(){
		return poDao.getAll();
	}
	
	public PurchaseOrder getOne(Long id) {
		
		PurchaseOrder puchaseOrder = poDao.getOne(id);
		return puchaseOrder;
	}
	
	public void update(PurchaseOrder po) {
		User user = (User) httpSession.getAttribute("userLogin");
		PurchaseOrder oldPo = poDao.getOne(po.getId());
		List<PurchaseOrderDetail> poDetail = po.getPurchaseOrderDetail();
		po.setPurchaseOrderDetail(null);
		po.setCreatedOn(oldPo.getCreatedOn());
		po.setModifiedOn(new Date());
		po.setCreatedBy(oldPo.getCreatedBy());
		po.setModifiedBy(user);
		poDao.update(po);
		
		for(PurchaseOrderDetail pod : poDetail) {
			pod.setPurchaseOrder(po);
			if (pod.getId() == null) {
				podDao.save(pod);
			} else {
				podDao.update(pod);
			}
		}
		System.out.println(po.getStatus());
		System.out.println(po.getStatus().equals("Created"));
		if (po.getStatus().equals("Created")) {
			System.out.println("Status Tidak Berubah");

		} else {
			System.out.println("Status Berubah");
			PurchaseOrderHistory poh = new PurchaseOrderHistory();
			poh.setPurchaseOrder(po);
			poh.setStatus(po.getStatus());
			poh.setCreatedOn(po.getCreatedOn());
			pohDao.save(poh);
		}
	}
	
	public ItemInventory getInventoryByVariantAndOutletId(Long poId, Long podId){
		PurchaseOrder purchaseOrder = poDao.getOne(poId);
		PurchaseOrderDetail poDetail = podDao.getOne(podId);
		return invDao.searchInventoryByVariantAndOutletId(poDetail.getVariant().getId(), purchaseOrder.getOutlet().getId());
	}

	public List<PurchaseOrder> getAllPoByOutlet(Long outletId) {
		return poDao.getAllPOByOutlet(outletId);
	}

	public List<PurchaseOrderDetail> getPODetailByPOIdandOutletId(Long poId) {
		return podDao.getPODetailByPOIdandOutletId(poId);
	}

	public void approve(long id) {
		poDao.approve(id);
		PurchaseOrder po = poDao.getOne(id);
		PurchaseOrderHistory poh = new PurchaseOrderHistory();
		poh.setCreatedOn(new Date());
		poh.setPurchaseOrder(po);
		poh.setStatus(po.getStatus());
		pohDao.save(poh);
	}

	public void reject(long id) {
		poDao.reject(id);
		PurchaseOrder po = poDao.getOne(id);
		PurchaseOrderHistory poh = new PurchaseOrderHistory();
		poh.setCreatedOn(new Date());
		poh.setPurchaseOrder(po);
		poh.setStatus(po.getStatus());
		pohDao.save(poh);
	}
	public void process(long id) {
		poDao.process(id);
		PurchaseOrder po = poDao.getOne(id);
		PurchaseOrderHistory poh = new PurchaseOrderHistory();
		poh.setCreatedOn(new Date());
		poh.setPurchaseOrder(po);
		poh.setStatus(po.getStatus());
		pohDao.save(poh);
	}
	

}
