package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.TransferStockHistoryDao;
import com.bootcamp.miniproject.model.TransferStockHistory;

@Service
@Transactional
public class TransferStockHistoryService {

	@Autowired
	TransferStockHistoryDao transferStockHistoryDao;
	
	public void save(TransferStockHistory transferStock) {
		transferStockHistoryDao.save(transferStock);
	}
	
	public void delete(TransferStockHistory transferStock) {
		transferStockHistoryDao.delete(transferStock);
	}
	
	public void update(TransferStockHistory transferStock) {
		transferStockHistoryDao.update(transferStock);
	}
	
	public List<TransferStockHistory> selectAll(){
		return transferStockHistoryDao.selectAll();
	}
	
	public void saveUpdate(TransferStockHistory transferStock) {
		transferStockHistoryDao.saveUpdate(transferStock);
	}
	
	public TransferStockHistory getOne(long id) {
		TransferStockHistory transferStock = new TransferStockHistory();
		transferStock.setId(id);
		return transferStockHistoryDao.getOne(transferStock);
	}
	
	public List<TransferStockHistory> getTransferStockHistorysByTransferStockId(long search){
		return transferStockHistoryDao.getTransferStockHistoryByTransferStockId(search);
	}
}
