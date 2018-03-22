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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "mp_t_purchase_request")
public class PurchaseRequest {
	
	public PurchaseRequest() {
		this.createOn = new Date();
		this.modifiedOn = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
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
	
	@OneToMany (fetch=FetchType.LAZY, mappedBy="purchaseRequest", cascade=CascadeType.ALL, orphanRemoval=true)
	List<PurchaseRequestDetail> purchaseRequestDetail;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<PurchaseRequestDetail> getPurchaseRequestDetail() {
		return purchaseRequestDetail;
	}

	public void setPurchaseRequestDetail(List<PurchaseRequestDetail> purchaseRequestDetail) {
		this.purchaseRequestDetail = purchaseRequestDetail;
	}
	
	// Purchase Orde
	
	
	// Setter Getter
	
	
	
}
