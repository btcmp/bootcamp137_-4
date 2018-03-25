package com.bootcamp.miniproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "mp_t_purchase_order_detail")
public class PurchaseOrderDetail {
	public PurchaseOrderDetail() {
		this.createOn = new Date();
		this.modifiedOn = new Date();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@ManyToOne
	private PurchaseOrder purchaseOrder;
	
	@ManyToOne
	private ItemVariant variant;
	
	@Column(name = "request_qty")
	private int requestQty;
	
	@Column(name = "unit_cost")
	private float unitCost;
	
	@Column(name = "sub_total")
	private float subTotal;
	
	@Column(name="create_by")
	private long createdBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_on")
	private Date createOn;
	
	@Column(name="modified_by")
	private long modifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_on")
	private Date modifiedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public ItemVariant getVariant() {
		return variant;
	}

	public void setVariant(ItemVariant variant) {
		this.variant = variant;
	}

	public int getRequestQty() {
		return requestQty;
	}

	public void setRequestQty(int requestQty) {
		this.requestQty = requestQty;
	}

	public float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
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

	
}
