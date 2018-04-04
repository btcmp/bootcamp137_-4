package com.bootcamp.miniproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="MP_T_SALES_ORDER_DETAIL")
public class SalesOrderDetail {

	public SalesOrderDetail() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	@NotNull
	@ManyToOne
	private SalesOrder salesOrder;
	@NotNull
	@ManyToOne
	@JoinColumn(name="item_variant")
	private ItemVariant itemVariant;
	@ManyToOne
	@JoinColumn(name="item_inventory")
	private ItemInventory itemInventory;
	@NotNull
	private int qty;
	@NotNull
	@Column(name="unit_price")
	private float unitPrice;
	@Column(name="sub_total")
	private float subTotal;
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
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}
	public ItemVariant getItemVariant() {
		return itemVariant;
	}
	public void setItemVariant(ItemVariant itemVariant) {
		this.itemVariant = itemVariant;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
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
