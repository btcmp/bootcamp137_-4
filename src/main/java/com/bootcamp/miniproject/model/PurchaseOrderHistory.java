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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bootcamp.miniproject.model.User;


@Entity
@Table(name = "mp_t_po_history")
public class PurchaseOrderHistory {
	public PurchaseOrderHistory() {
		this.createdOn = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@ManyToOne
	@JsonIgnore
	private PurchaseOrder purchaseOrder;
	
	private String status;
	@ManyToOne
	@JoinColumn(name="created_by")
	@JsonIgnore
	private User createdBy;
	@Temporal(TemporalType.DATE)
	@Column(name="created_on")
	private Date createdOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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
	
	
}
