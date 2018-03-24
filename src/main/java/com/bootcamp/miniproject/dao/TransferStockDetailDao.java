package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.TransferStockDetail;

public interface TransferStockDetailDao {

	public void save(TransferStockDetail transferStockDetail);
	
	public List<TransferStockDetail> selectAll();
	
	public TransferStockDetail getOne(TransferStockDetail transferStockDetail);
	
	public void update(TransferStockDetail transferStockDetail);
	
	public void saveUpdate(TransferStockDetail transferStockDetail);
	
	public void delete(TransferStockDetail transferStockDetail);
	
	public List<TransferStockDetail> getTransferStockByTransferStockId(long search);
}
