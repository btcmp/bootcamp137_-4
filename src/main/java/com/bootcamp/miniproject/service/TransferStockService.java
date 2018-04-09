package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.ItemInventoryDao;
import com.bootcamp.miniproject.dao.TransferStockDao;
import com.bootcamp.miniproject.dao.TransferStockDetailDao;
import com.bootcamp.miniproject.dao.TransferStockHistoryDao;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.ItemVariant;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.model.TransferStockDetail;
import com.bootcamp.miniproject.model.TransferStockHistory;

@Service
@Transactional
public class TransferStockService {
	@Autowired
	TransferStockDao transferStockDao;
	
	@Autowired
	TransferStockDetailDao transferStockDetailDao;
	
	@Autowired
	ItemInventoryDao itemInventoryDao;
	
	@Autowired
	TransferStockHistoryDao transferStockHistoryDao;
	
	public void save(TransferStock transferStock) {
		//transferStockDao.save(transferStock);
		
		TransferStock trans = new TransferStock();
		trans.setFromOutlet(transferStock.getFromOutlet());
		trans.setToOutlet(transferStock.getToOutlet());
		trans.setNotes(transferStock.getNotes());
		trans.setStatus(transferStock.getStatus());
		trans.setCreatedBy(transferStock.getCreatedBy());
		trans.setModifiedBy(transferStock.getModifiedBy());
		transferStockDao.save(trans);
		
		for(TransferStockDetail tsd : transferStock.getTransferStockDetail()) {
			ItemVariant itemVariant = new ItemVariant();
			itemVariant.setId(tsd.getItemVariant().getId());
			ItemInventory itemInventory = new ItemInventory();
			itemInventory.setId(tsd.getItemInventory().getId());
			TransferStockDetail transDetail = new TransferStockDetail();
			transDetail.setItemVariant(itemVariant);
			transDetail.setItemInventory(itemInventory);
			transDetail.setInstock(tsd.getInstock());
			transDetail.setTransferQty(tsd.getTransferQty());
			transDetail.setTransferStock(trans);
			transDetail.setCreatedBy(tsd.getCreatedBy());
			transDetail.setModifiedBy(tsd.getModifiedBy());
			transferStockDetailDao.save(transDetail);
		}
		
		TransferStockHistory tsh = new TransferStockHistory();
		tsh.setStatus(trans.getStatus());
		tsh.setTransferStock(trans);
		transferStockHistoryDao.save(tsh);
	}
	
	public void delete(TransferStock transferStock) {
		transferStockDao.delete(transferStock);
	}
	
	public void update(TransferStock transferStock) {
		transferStockDao.update(transferStock);
	}
	
	public List<TransferStock> selectAll(){
		return transferStockDao.selectAll();
	}
	
	public void saveUpdate(TransferStock transferStock) {
		transferStockDao.saveUpdate(transferStock);
		TransferStockHistory tsh = new TransferStockHistory();
		tsh.setStatus(transferStock.getStatus());
		tsh.setTransferStock(transferStock);
		transferStockHistoryDao.save(tsh);
	}
	
	public TransferStock getOne(long id) {
		TransferStock transferStock = new TransferStock();
		transferStock.setId(id);
		return transferStockDao.getOne(transferStock);
	}

	public List<TransferStock> getTransferStockByOutletId(long search) {
		// TODO Auto-generated method stub
		return transferStockDao.getTransferStockByOutletId(search);
	}
	
	public List<TransferStock> getTransferStockByFromOutletId(long search) {
		// TODO Auto-generated method stub
		return transferStockDao.getTransferStockByFromOutletId(search);
	}

	public List<TransferStock> getTransferStockByFromOutletAndToOutletId(long fromOutletId, long toOutletId) {
		// TODO Auto-generated method stub
		return transferStockDao.getTransferStockByFromOutletAndToOutletId(fromOutletId, toOutletId);
	}

	public void saveUpdateStatusAndStock(TransferStock transferStock) {
		transferStockDao.saveUpdate(transferStock);
		TransferStockHistory tsh = new TransferStockHistory();
		tsh.setStatus(transferStock.getStatus());
		tsh.setTransferStock(transferStock);
		transferStockHistoryDao.save(tsh);
		
		long idToOutlet = transferStock.getToOutlet().getId();
		List<TransferStockDetail> TSD = transferStockDetailDao.getTransferStockByTransferStockId(transferStock.getId());
		for(TransferStockDetail TSDetail : TSD) {
			long variantId = TSDetail.getItemVariant().getId();
			ItemInventory invent = TSDetail.getItemInventory();
			invent.setEndingQty(invent.getEndingQty()-TSDetail.getTransferQty());
			
			ItemInventory iv = itemInventoryDao.searchInventoryByVariantAndOutletId(variantId, idToOutlet);
			if (iv != null) {
				iv.setEndingQty(iv.getEndingQty()+TSDetail.getTransferQty());
			} else {
				ItemInventory ivNew = new ItemInventory();
				ivNew.setAdjustmentQty(0);
				ivNew.setAlertAtQty(1);
				ivNew.setBeginning(TSDetail.getTransferQty());
				ivNew.setEndingQty(TSDetail.getTransferQty());
				ivNew.setItemVariant(TSDetail.getItemVariant());
				ivNew.setOutlet(transferStock.getToOutlet());
				/*ivNew.setCreatedBy(0);
				ivNew.setModifiedBy(0);*/
				ivNew.setPurchaseQty(0);
				ivNew.setSalesOrderQty(0);
				ivNew.setTransferStockQty(0);
				itemInventoryDao.save(ivNew);
			}
		}
	}
}
