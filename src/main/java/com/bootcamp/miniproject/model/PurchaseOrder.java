package com.bootcamp.miniproject.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

@Entity
@Table(name = "mp_t_purchase_order")
public class PurchaseOrder {
	public PurchaseOrder() {
		this.modifiedOn = new Date();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@OneToMany (fetch=FetchType.LAZY, mappedBy="purchaseOrder", cascade=CascadeType.ALL, orphanRemoval=true)
	List<PurchaseOrderDetail> purchaseOrderDetail;
	
	@OneToMany (fetch=FetchType.LAZY, mappedBy="purchaseOrder", cascade=CascadeType.ALL, orphanRemoval=true)
	List<PurchaseOrderHistory> purchaseOrderHistory;
	
	@OneToOne
	@JoinColumn
	@JsonBackReference
	//@JsonIgnore
	private PurchaseRequest purchaseRequest;
		
	@NotNull
	@ManyToOne
	private Outlet outlet;
	
	@NotNull
	@ManyToOne
	private Supplier supplier;
	
	@Column(name = "po_no")
	private String poNo;
	
	@Nullable
	private String notes;
	private String status;
	
	@Column(name = "grand_total")
	private float grandTotal;
	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_on")
	private Date createdOn;
	@ManyToOne
	@JoinColumn(name="modified_by")
	private User modifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_on")
	private Date modifiedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PurchaseRequest getPurchaseRequest() {
		return purchaseRequest;
	}

	public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}

	public Outlet getOutlet() {
		return outlet;
	}

	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	public List<PurchaseOrderDetail> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(List<PurchaseOrderDetail> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}

	public List<PurchaseOrderHistory> getPurchaseOrderHistory() {
		return purchaseOrderHistory;
	}

	public void setPurchaseOrderHistory(List<PurchaseOrderHistory> purchaseOrderHistory) {
		this.purchaseOrderHistory = purchaseOrderHistory;
	}
	
	
}
