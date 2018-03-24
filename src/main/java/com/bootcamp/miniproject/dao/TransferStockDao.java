package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.TransferStock;

public interface TransferStockDao {

	public void save(TransferStock transferStock);
	
	public List<TransferStock> selectAll();
	
	public TransferStock getOne(TransferStock transferStock);
	
	public void update(TransferStock transferStock);
	
	public void saveUpdate(TransferStock transferStock);
	
	public void delete(TransferStock transferStock);
	
	public List<TransferStock> getTransferStockByOutletId(String search);
}
