package com.bootcamp.miniproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="MP_T_TRANSFER_STOCK_DETAIL")
public class TransferStockDetail {
	
	public TransferStockDetail() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	//@NotNull
	@ManyToOne
	@JoinColumn(name="transfer_stock")
	private TransferStock transferStock;
	private int instock;
	@NotNull
	@ManyToOne
	@JoinColumn(name="item_variant")
	private ItemVariant itemVariant;
	@ManyToOne
	@JoinTable(name="item_inventory")
	private ItemInventory itemInventory;
	@NotNull
	@Column(name="transfer_qty")
	private int transferQty;
	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;
	@Column(name="created_on")
	private Date createdOn;
	@ManyToOne
	@JoinColumn(name="modified_by")
	private User modifiedBy;
	@Column(name="modified_on")
	private Date modifiedOn;
	
	public ItemInventory getItemInventory() {
		return itemInventory;
	}
	public void setItemInventory(ItemInventory itemInventory) {
		this.itemInventory = itemInventory;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TransferStock getTransferStock() {
		return transferStock;
	}
	public void setTransferStock(TransferStock transferStock) {
		this.transferStock = transferStock;
	}
	public int getInstock() {
		return instock;
	}
	public void setInstock(int instock) {
		this.instock = instock;
	}
	public ItemVariant getItemVariant() {
		return itemVariant;
	}
	public void setItemVariant(ItemVariant itemVariant) {
		this.itemVariant = itemVariant;
	}
	public int getTransferQty() {
		return transferQty;
	}
	public void setTransferQty(int transferQty) {
		this.transferQty = transferQty;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public User getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
