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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mp_t_purchase_request")
public class PurchaseRequest {
	
	public PurchaseRequest() {
		this.modifiedOn = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@ManyToOne
	private Outlet outlet;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ready_time")
	private Date readyTime;
	
	@Column(name="pr_no")
	@NotEmpty
	private String prNo;
	
	private String notes;
	@NotEmpty
	private String status;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	@JsonIgnore
	private User createdBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_on")
	private Date createdOn;
	@ManyToOne
	@JoinColumn(name="modified_by")
	@JsonIgnore
	private User modifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_on")
	private Date modifiedOn;
	
	@OneToMany (fetch=FetchType.LAZY, mappedBy="purchaseRequest", cascade=CascadeType.ALL)
	private List<PurchaseRequestDetail> purchaseRequestDetail;
	
	@OneToMany (fetch=FetchType.LAZY, mappedBy="purchaseRequest", cascade=CascadeType.ALL)
	private List<PurchaseRequestHistory> purchaseRequestHistory;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="purchaseRequest", cascade=CascadeType.ALL, orphanRemoval=true)
	private PurchaseOrder purchaseOrder;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Outlet getOutlet() {
		return outlet;
	}

	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}

	public Date getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(Date readyTime) {
		this.readyTime = readyTime;
	}

	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
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

	public List<PurchaseRequestDetail> getPurchaseRequestDetail() {
		return purchaseRequestDetail;
	}

	public void setPurchaseRequestDetail(List<PurchaseRequestDetail> purchaseRequestDetail) {
		this.purchaseRequestDetail = purchaseRequestDetail;
	}

	public List<PurchaseRequestHistory> getPurchaseRequestHistory() {
		return purchaseRequestHistory;
	}

	public void setPurchaseRequestHistory(List<PurchaseRequestHistory> purchaseRequestHistory) {
		this.purchaseRequestHistory = purchaseRequestHistory;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	
}
