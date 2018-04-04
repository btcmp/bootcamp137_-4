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

@Entity
@Table(name="MP_T_TRANSFER_STOCK_HISTORY")
public class TransferStockHistory {

	public TransferStockHistory() {
		this.createdOn = new Date();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	private String status;
	@Column(name="created_on")
	private Date createdOn;
	@ManyToOne
	@JoinColumn(name="transfer_stock")
	private TransferStock transferStock;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public TransferStock getTransferStock() {
		return transferStock;
	}
	public void setTransferStock(TransferStock transferStock) {
		this.transferStock = transferStock;
	}
	
}
