package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.TransferStockDao;
import com.bootcamp.miniproject.dao.TransferStockDetailDao;
import com.bootcamp.miniproject.model.ItemVariant;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.model.TransferStockDetail;

@Service
@Transactional
public class TransferStockService {
	@Autowired
	TransferStockDao transferStockDao;
	
	@Autowired
	TransferStockDetailDao transferStockDetailDao;
	
	public void save(TransferStock transferStock) {
		//transferStockDao.save(transferStock);
		
		TransferStock trans = new TransferStock();
		trans.setFromOutlet(transferStock.getFromOutlet());
		trans.setToOutlet(transferStock.getToOutlet());
		trans.setNotes(transferStock.getNotes());
		trans.setStatus(transferStock.getStatus());
		transferStockDao.save(trans);
		
		for(TransferStockDetail tsd : transferStock.getTransferStockDetail()) {
			ItemVariant itemVariant = new ItemVariant();
			itemVariant.setId(tsd.getItemVariant().getId());
			TransferStockDetail transDetail = new TransferStockDetail();
			transDetail.setItemVariant(itemVariant);
			transDetail.setInstock(tsd.getInstock());
			transDetail.setTransferQty(tsd.getTransferQty());
			transDetail.setTransferStock(trans);
			transferStockDetailDao.save(transDetail);
		}
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
	}
	
	public TransferStock getOne(long id) {
		TransferStock transferStock = new TransferStock();
		transferStock.setId(id);
		return transferStockDao.getOne(transferStock);
	}

	public List<TransferStock> getTransferStockByOutletId(String search) {
		// TODO Auto-generated method stub
		return transferStockDao.getTransferStockByOutletId(search);
	}
}
