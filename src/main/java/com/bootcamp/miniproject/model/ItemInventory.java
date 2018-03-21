package com.bootcamp.miniproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mp_mst_item_inventory")
public class ItemInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column(name = "outlet_id")
	private long outletId;
	
	private int beginning;
	
	@Column(name = "purchase_Qty")
	private int purchaseQty;
	
	@Column(name = "sales_order_qty")
	private int salesOrderQty;
	
	@Column(name = "transfer_stock_qty")
	private int transferStockQty;
	
	@Column(name = "adjustment_qty")
	private int adjustmentQty;
	
	@Column(name = "ending_qty")
	private int endingQty;
	
	@Column(name = "created_by")
	private long createdBy;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "modified_by")
	private long modifiedBy;
	
	@Column(name = "modified_on")
	private Date modifiedOn;
	
	/* Relasi dengan variant */
	@ManyToOne
	private ItemVariant itemVariant;
	
	
	
	/*  Relasi dengan outlet */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOutletId() {
		return outletId;
	}

	public void setOutletId(long outletId) {
		this.outletId = outletId;
	}

	public int getBeginning() {
		return beginning;
	}

	public void setBeginning(int beginning) {
		this.beginning = beginning;
	}

	public int getPurchaseQty() {
		return purchaseQty;
	}

	public void setPurchaseQty(int purchaseQty) {
		this.purchaseQty = purchaseQty;
	}

	public int getSalesOrderQty() {
		return salesOrderQty;
	}

	public void setSalesOrderQty(int salesOrderQty) {
		this.salesOrderQty = salesOrderQty;
	}

	public int getTransferStockQty() {
		return transferStockQty;
	}

	public void setTransferStockQty(int transferStockQty) {
		this.transferStockQty = transferStockQty;
	}

	public int getAdjustmentQty() {
		return adjustmentQty;
	}

	public void setAdjustmentQty(int adjustmentQty) {
		this.adjustmentQty = adjustmentQty;
	}

	public int getEndingQty() {
		return endingQty;
	}

	public void setEndingQty(int endingQty) {
		this.endingQty = endingQty;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public ItemVariant getItemVariant() {
		return itemVariant;
	}

	public void setItemVariant(ItemVariant itemVariant) {
		this.itemVariant = itemVariant;
	}
	
	
}
