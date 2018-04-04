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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="MP_T_SALES_ORDER")
public class SalesOrder {

	public SalesOrder() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	@NotNull
	@ManyToOne
	private Customer customer;
	@NotNull
	@Column(name="grand_total")
	private float grandTotal;
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
	@OneToMany(mappedBy="salesOrder", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<SalesOrderDetail> salesOrderDetail;
	
	public List<SalesOrderDetail> getSalesOrderDetail() {
		return salesOrderDetail;
	}
	public void setSalesOrderDetail(List<SalesOrderDetail> salesOrderDetail) {
		this.salesOrderDetail = salesOrderDetail;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
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
