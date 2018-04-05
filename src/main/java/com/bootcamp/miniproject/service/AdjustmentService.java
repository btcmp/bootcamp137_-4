package com.bootcamp.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.miniproject.dao.AdjustmentDao;
import com.bootcamp.miniproject.dao.AdjustmentDetailDao;
import com.bootcamp.miniproject.dao.AdjustmentHistoryDao;
import com.bootcamp.miniproject.dao.ItemInventoryDao;
import com.bootcamp.miniproject.model.Adjustment;
import com.bootcamp.miniproject.model.AdjustmentDetail;
import com.bootcamp.miniproject.model.AdjustmentHistory;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.ItemVariant;


@Service
@Transactional
public class AdjustmentService {

	@Autowired
	AdjustmentDao adjustmentDao;
	
	@Autowired 
	AdjustmentDetailDao adjustmentDetailDao;
	
	@Autowired
	ItemInventoryDao itemInventoryDao;
	
	@Autowired
	AdjustmentHistoryDao adjustmentHistoryDao;
	
	public void save(Adjustment adjustment) {
		//object adjustment
		Adjustment adjust = new Adjustment();
		adjust.setOutlet(adjustment.getOutlet());
		adjust.setNotes(adjustment.getNotes());
		adjust.setStatus(adjustment.getStatus());
		adjust.setCreatedBy(adjustment.getCreatedBy());
		adjust.setModifiedBy(adjustment.getModifiedBy());
		adjustmentDao.save(adjust);
		
		//object adjustment detail
		for(AdjustmentDetail adjustDet : adjustment.getAdjustmentDetails()) {
			ItemVariant itemVariant = new ItemVariant();
			itemVariant.setId(adjustDet.getItemVariant().getId());
			AdjustmentDetail adjustDetail = new AdjustmentDetail();
			adjustDetail.setItemVariant(itemVariant);
			adjustDetail.setInStock(adjustDet.getInStock());
			adjustDetail.setActualStock(adjustDet.getActualStock());
			adjustDetail.setAdjustment(adjust);
			adjustDetail.setCreatedBy(adjustDet.getCreatedBy());
			adjustDetail.setModifiedBy(adjustDet.getModifiedBy());
			adjustmentDetailDao.save(adjustDetail);
		}
		
		//object adjustment history 
			AdjustmentHistory adjustHis = new AdjustmentHistory();
			adjustHis.setAdjustment(adjust);
			adjustHis.setStatus(adjust.getStatus());
			adjustHis.setCreatedBy(adjust.getCreatedBy());
			adjustmentHistoryDao.save(adjustHis);
				
		
	}
	
	public List<Adjustment> selectAll(){
		return adjustmentDao.selectAll();
	}
	
	public Adjustment getOne(long id) {
		Adjustment adjustment = new Adjustment();
		adjustment.setId(id);
		return adjustmentDao.getOne(adjustment);
	}
	
	public void update(Adjustment adjustment) {
		adjustmentDao.update(adjustment);
		AdjustmentHistory adjustHis = new AdjustmentHistory();
		adjustHis.setAdjustment(adjustment);
		adjustHis.setStatus(adjustment.getStatus());
		adjustmentHistoryDao.save(adjustHis);

	}

	public void delete(Adjustment adjustment) {
		adjustmentDao.delete(adjustment);
	}

	public void updateStatusAndStock(Adjustment adjustment) {
		adjustmentDao.update(adjustment);
		AdjustmentHistory adjustHis = new AdjustmentHistory();
		adjustHis.setAdjustment(adjustment);
		adjustHis.setStatus(adjustment.getStatus());
		adjustmentHistoryDao.save(adjustHis);
		
		long idOutlet = adjustment.getOutlet().getId();
		List<AdjustmentDetail> adjustDetails = adjustment.getAdjustmentDetails();
		for(AdjustmentDetail ad : adjustDetails) {
			long idVariant = ad.getItemVariant().getId();
			ItemInventory itemInventory = itemInventoryDao.searchInventoryByVariantAndOutletId(idVariant, idOutlet);
			itemInventory.setEndingQty(ad.getActualStock());
		}

	}

	public List<Adjustment> getAdjustmentIdByOutletId(long outletId) {
		// TODO Auto-generated method stub
		return adjustmentDao.getAdjustmentIdByOutletId(outletId);
	}
}
