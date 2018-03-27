package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.TransferStockDetailDao;
import com.bootcamp.miniproject.model.TransferStockDetail;

@Service
@Transactional
public class TransferStockDetailService {

	@Autowired
	TransferStockDetailDao transferStockDetailDao;
	
	public void save(TransferStockDetail transferStock) {
		transferStockDetailDao.save(transferStock);
	}
	
	public void delete(TransferStockDetail transferStock) {
		transferStockDetailDao.delete(transferStock);
	}
	
	public void update(TransferStockDetail transferStock) {
		transferStockDetailDao.update(transferStock);
	}
	
	public List<TransferStockDetail> selectAll(){
		return transferStockDetailDao.selectAll();
	}
	
	public void saveUpdate(TransferStockDetail transferStock) {
		transferStockDetailDao.saveUpdate(transferStock);
	}
	
	public TransferStockDetail getOne(long id) {
		TransferStockDetail transferStock = new TransferStockDetail();
		transferStock.setId(id);
		return transferStockDetailDao.getOne(transferStock);
	}
	
	public List<TransferStockDetail> getTransferStockDetailsByTransferStockId(long search){
		return transferStockDetailDao.getTransferStockByTransferStockId(search);
	}
}
