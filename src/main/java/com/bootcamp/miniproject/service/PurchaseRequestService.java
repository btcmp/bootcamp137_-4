package com.bootcamp.miniproject.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.PurchaseOrderDao;
import com.bootcamp.miniproject.dao.PurchaseOrderDetailDao;
import com.bootcamp.miniproject.dao.PurchaseOrderHistoryDao;
import com.bootcamp.miniproject.dao.PurchaseRequestDao;
import com.bootcamp.miniproject.dao.PurchaseRequestDetailDao;
import com.bootcamp.miniproject.dao.PurchaseRequestHistoryDao;
import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseOrderDetail;
import com.bootcamp.miniproject.model.PurchaseOrderHistory;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.PurchaseRequestDetail;
import com.bootcamp.miniproject.model.PurchaseRequestHistory;
import com.bootcamp.miniproject.model.User;

@Service
@Transactional
public class PurchaseRequestService {
	
	@Autowired
	PurchaseRequestDao prDao;
	
	@Autowired
	PurchaseRequestDetailDao prDetailDao;
	
	@Autowired
	PurchaseRequestHistoryDao prHistoryDao;
	
	@Autowired
	PurchaseOrderDao poDao;
	
	@Autowired
	PurchaseOrderDetailDao poDetailDao;
	
	@Autowired
	PurchaseOrderHistoryDao poHistoryDao;
	
	@Autowired
	HttpSession httpSession;
	
	public List<PurchaseRequest> getAll() {
		return prDao.getAll();
	}
	
	public PurchaseRequest getOne(long id) {
		PurchaseRequest pr = prDao.getOne(id);
		System.out.println("Berhasil buka");
		List<PurchaseRequestDetail> prd = prDetailDao.getPRDetailByPRIdandOutletId(id, pr.getOutlet().getId());
		//List<PurchaseRequestHistory> prh = prHistoryDao.getPRHistoryByPR(id);
		if (prd.isEmpty()) {
			
		} else {
			pr.setPurchaseRequestDetail(prd);
			System.out.println("berhasil set pr detail");
		}
		return pr;
	}
	
	public void save(PurchaseRequest pr) {
		User user = (User) httpSession.getAttribute("userLogin");
		
		Calendar cal = Calendar.getInstance();
		int monthInt = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		//System.out.println(cal.get(Calendar.YEAR)+ ' ' + cal.get(Calendar.MONTH));
		String month;
		if (monthInt < 10) {
			month = "0"+monthInt;
		} else {
			month = Integer.toString(monthInt);
		}
		int num = prDao.CountPRByMonth(monthInt, year)+1;
		String no;
		if(num < 10) {
			no = "00"+num;
		} else if(num < 100) {
			no = "0"+num;
		} else {
			no = Integer.toString(num);
		}
		
		String prNo = "PR"+year+month+no;
		
		System.out.println("Msuk Service");
		List<PurchaseRequestDetail> prDetail = pr.getPurchaseRequestDetail();
		pr.setPurchaseRequestDetail(null);
		pr.setPrNo(prNo);
		pr.setCreatedBy(user);
		pr.setCreatedOn(new Date());
		pr.setModifiedBy(user);
		prDao.save(pr);
		
		for(PurchaseRequestDetail prd : prDetail) {
			prd.setPurchaseRequest(pr);
			prd.setCreatedBy(user);
			prd.setModifiedBy(user);
			prd.setCreatedOn(new Date());
			prDetailDao.save(prd);
		}
		PurchaseRequestHistory prh = new PurchaseRequestHistory();
		prh.setPurchaseRequest(pr);
		prh.setStatus(pr.getStatus());
		prh.setCreatedOn(pr.getCreatedOn());
		prh.setCreatedBy(user);
		prHistoryDao.save(prh);
	}

	public void update(PurchaseRequest pr) {
		User user = (User) httpSession.getAttribute("userLogin");
		List<PurchaseRequestDetail> prDetail = pr.getPurchaseRequestDetail();
		pr.setPurchaseRequestDetail(null);
		pr.setModifiedBy(user);
		prDao.update(pr);
		
		for(PurchaseRequestDetail prd : prDetail) {
			prd.setPurchaseRequest(pr);
			if (prd.getId() == null) {
				prd.setCreatedBy(user);
				prd.setModifiedBy(user);
				prd.setCreatedOn(new Date());
				prDetailDao.save(prd);
			} else {
				prd.setModifiedBy(user);
				prDetailDao.update(prd);
			}
		}
		if (pr.getStatus().equals("Created")) {
			System.out.println("Status Tidak Berubah");

		} else {
			System.out.println("Status Berubah");
			PurchaseRequestHistory prh = new PurchaseRequestHistory();
			prh.setPurchaseRequest(pr);
			prh.setStatus(pr.getStatus());
			prh.setCreatedOn(pr.getCreatedOn());
			prh.setCreatedBy(user);
			prHistoryDao.save(prh);
		}
		
		
	}
	public void submit(PurchaseRequest pr) {
		
		
	}
	public void delete(PurchaseRequest pr) {
		prDao.delete(pr);
	}

	public List<PurchaseRequestDetail> getPRDetailByPRIdandOutletId(Long prId, Long outletId) {
		return prDetailDao.getPRDetailByPRIdandOutletId(prId, outletId);
	}

	public List<PurchaseRequest> getAllPrByOutlet(Long outletId) {
		return prDao.getAllPrByOutlet(outletId);
	}

	public void approve(long id) {
		User user = (User) httpSession.getAttribute("userLogin");
		
		prDao.approve(id);
		PurchaseRequest pr = prDao.getOne(id);
		PurchaseRequestHistory prh = new PurchaseRequestHistory();
		prh.setCreatedOn(new Date());
		prh.setCreatedBy(user);
		prh.setPurchaseRequest(pr);
		prh.setStatus(pr.getStatus());
		prHistoryDao.save(prh);
	}


	public void createPo(long id) {
		User user = (User) httpSession.getAttribute("userLogin");
		
		// Update Status Pr
		prDao.createPo(id);
		PurchaseRequest pr = prDao.getOne(id);
		//
		
		
		PurchaseRequestHistory prHist = new PurchaseRequestHistory();
		prHist.setCreatedOn(new Date());
		prHist.setPurchaseRequest(pr);
		prHist.setStatus(pr.getStatus());
		prHist.setCreatedBy(user);
		prHistoryDao.save(prHist);
		
		Calendar cal = Calendar.getInstance();
		int monthInt = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		String month;
		if (monthInt < 10) {
			month = "0"+monthInt;
		} else {
			month = Integer.toString(monthInt);
		}
		int num = poDao.CountPRByMonth(monthInt, year)+1;
		String no;
		if(num < 10) {
			no = "00"+num;
		} else if(num < 100) {
			no = "0"+num;
		} else {
			no = Integer.toString(num);
		}
		
		String poNo = "PO"+year+month+no;
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setCreatedOn(new Date());
		purchaseOrder.setNotes(pr.getNotes());
		purchaseOrder.setPoNo(poNo);
		purchaseOrder.setPurchaseRequest(pr);
		purchaseOrder.setStatus("Created");
		purchaseOrder.setCreatedBy(user);
		purchaseOrder.setModifiedBy(user);
		purchaseOrder.setCreatedOn(new Date());
		purchaseOrder.setOutlet(pr.getOutlet());
		poDao.save(purchaseOrder);
		float grandTotal = 0;
		if(pr.getPurchaseRequestDetail() == null) {
			
		}else {
			for(PurchaseRequestDetail prd : pr.getPurchaseRequestDetail()) {
				PurchaseOrderDetail poDetail = new PurchaseOrderDetail();
				poDetail.setCreatedOn(purchaseOrder.getCreatedOn());
				poDetail.setPurchaseOrder(purchaseOrder);
				poDetail.setUnitCost(prd.getItemVariant().getPrice());
				poDetail.setSubTotal(prd.getRequestQty()*prd.getItemVariant().getPrice());
				poDetail.setRequestQty(prd.getRequestQty());
				poDetail.setVariant(prd.getItemVariant());
				poDetail.setCreatedBy(user);
				poDetail.setModifiedBy(user);
				poDetailDao.save(poDetail);
				grandTotal += prd.getRequestQty()*prd.getItemVariant().getPrice();
			}
		}
		PurchaseOrder newPo = poDao.getOne(purchaseOrder.getId());
		System.out.println(newPo.getId());
		newPo.setGrandTotal(grandTotal);
		poDao.update(newPo);
		System.out.println("hi");
		PurchaseOrderHistory poHistory = new PurchaseOrderHistory();
		poHistory.setCreatedOn(purchaseOrder.getCreatedOn());
		poHistory.setPurchaseOrder(purchaseOrder);
		poHistory.setStatus(purchaseOrder.getStatus());
		poHistory.setCreatedBy(user);
		poHistoryDao.save(poHistory);
	}
	
	public void reject(long id) {
		User user = (User) httpSession.getAttribute("userLogin");
		
		prDao.reject(id);
		PurchaseRequest pr = prDao.getOne(id);
		PurchaseRequestHistory prh = new PurchaseRequestHistory();
		prh.setCreatedOn(new Date());
		prh.setPurchaseRequest(pr);
		prh.setStatus(pr.getStatus());
		prh.setCreatedBy(user);
		prHistoryDao.save(prh);
		
	}
	public List<PurchaseRequest> getPRByStatus(Long outletId, String status) {
		return prDao.searchPRByStatus(outletId, status);
	}

	public List<PurchaseRequest> search(String search) {
		return prDao.searchPR(search);
	}
	
}
