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
		PurchaseOrder tempPo = new PurchaseOrder();
		tempPo.setId(po.getId());
		tempPo.setNotes(po.getNotes());
		tempPo.setPoNo(po.getPoNo());
		tempPo.setGrandTotal(po.getGrandTotal());
		tempPo.setStatus(po.getStatus());
		tempPo.setSupplier(po.getSupplier());
		tempPo.setOutlet(po.getOutlet());
		tempPo.setModifiedBy(user);
		tempPo.setModifiedOn(new Date());
		PurchaseOrder oldPo = poDao.getOne(po.getId());
		tempPo.setCreatedBy(oldPo.getCreatedBy());
		tempPo.setCreatedOn(oldPo.getCreatedOn());
		tempPo.setPurchaseRequest(oldPo.getPurchaseRequest());
//		
		
//		po.setPurchaseOrderDetail(null);
//		
//		po.setPurchaseRequest(oldPo.getPurchaseRequest());
//		po.setCreatedOn(oldPo.getCreatedOn());
//		po.setCreatedBy(oldPo.getCreatedBy());
//		
//		po.setModifiedBy(user);
//		
		poDao.update(tempPo);
//		
		List<PurchaseOrderDetail> poDetail = po.getPurchaseOrderDetail();
		for(PurchaseOrderDetail pod : poDetail) {
			pod.setPurchaseOrder(tempPo);
			if (pod.getId() == null) {
				pod.setCreatedBy(user);
				pod.setModifiedBy(user);
				pod.setCreatedOn(new Date());
				podDao.save(pod);
			} else {
				PurchaseOrderDetail oldPod = podDao.getOne(pod.getId());
				pod.setCreatedBy(oldPod.getCreatedBy());
				pod.setModifiedBy(user);
				pod.setCreatedOn(oldPod.getCreatedOn());
				pod.setModifiedOn(new Date());
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
			poh.setCreatedOn(new Date());
			poh.setCreatedBy(user);
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
		User user = (User) httpSession.getAttribute("userLogin");
		
		poDao.approve(id);
		PurchaseOrder po = poDao.getOne(id);
		PurchaseOrderHistory poh = new PurchaseOrderHistory();
		poh.setCreatedBy(user);
		poh.setCreatedOn(new Date());
		poh.setPurchaseOrder(po);
		poh.setStatus(po.getStatus());
		pohDao.save(poh);
	}

	public void reject(long id) {
		User user = (User) httpSession.getAttribute("userLogin");
		
		poDao.reject(id);
		PurchaseOrder po = poDao.getOne(id);
		PurchaseOrderHistory poh = new PurchaseOrderHistory();
		poh.setCreatedBy(user);
		poh.setCreatedOn(new Date());
		poh.setPurchaseOrder(po);
		poh.setStatus(po.getStatus());
		pohDao.save(poh);
	}
	public void process(long id) {
		User user = (User) httpSession.getAttribute("userLogin");
		
		poDao.process(id);
		PurchaseOrder po = poDao.getOne(id);
		PurchaseOrderHistory poh = new PurchaseOrderHistory();
		poh.setCreatedBy(user);
		poh.setCreatedOn(new Date());
		poh.setPurchaseOrder(po);
		poh.setStatus(po.getStatus());
		pohDao.save(poh);
	}

	public List<PurchaseOrder> getPOByStatus(Long outletId, String status) {
		return poDao.getPOByStatus(outletId, status);
	}
	
	public List<PurchaseOrder> searchPO(long outletId, String search){
		return poDao.searchPO(outletId, search);
	}
}
