package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.TransferStockHistory;

public interface TransferStockHistoryDao {
	
	public void save(TransferStockHistory transferStock);
	
	public List<TransferStockHistory> selectAll();
	
	public TransferStockHistory getOne(TransferStockHistory transferStock);
	
	public void update(TransferStockHistory transferStock);
	
	public void saveUpdate(TransferStockHistory transferStock);
	
	public void delete(TransferStockHistory transferStock);
	
	public List<TransferStockHistory> getTransferStockHistoryByTransferStockId(long search);
}
