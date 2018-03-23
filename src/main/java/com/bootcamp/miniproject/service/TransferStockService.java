package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.TransferStockDao;
import com.bootcamp.miniproject.model.TransferStock;

@Service
@Transactional
public class TransferStockService {
	@Autowired
	TransferStockDao transferStockDao;
	
	public void save(TransferStock transferStock) {
		transferStockDao.save(transferStock);
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
