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
@Table(name="MP_T_TRANSFER_STOCK")
public class TransferStock {

	public TransferStock() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	@NotNull
	@ManyToOne
	@JoinColumn(name="from_outlet")
	private Outlet fromOutlet;
	@NotNull
	@ManyToOne
	@JoinColumn(name="to_outlet")
	private Outlet toOutlet;
	private String notes;
	@NotNull
	private String status;
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
	@OneToMany(mappedBy="transferStock", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<TransferStockDetail> transferStockDetail;
	
	
	public List<TransferStockDetail> getTransferStockDetail() {
		return transferStockDetail;
	}
	public void setTransferStockDetail(List<TransferStockDetail> transferStockDetail) {
		this.transferStockDetail = transferStockDetail;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Outlet getFromOutlet() {
		return fromOutlet;
	}
	public void setFromOutlet(Outlet fromOutlet) {
		this.fromOutlet = fromOutlet;
	}
	public Outlet getToOutlet() {
		return toOutlet;
	}
	public void setToOutlet(Outlet toOutlet) {
		this.toOutlet = toOutlet;
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
