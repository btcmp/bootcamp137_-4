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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

@Entity
@Table(name="mp_mst_category")
public class Category {
	
	public Category() {     //konstruktor ini berfungsi untuk menyimpan Date pada database.
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	
	@NotNull
	private String name;
	
	@Column(name="created_by")
	private long createdBy;
	
	@Temporal(TemporalType.DATE)
	@Column (name="created_on")
	private Date createdOn;
	
	@Column (name="modified_by")
	private long modifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_on")
	private Date modifiedOn;
	
	@NotNull
	private boolean active;
	
	
	//fiels ini hanya bantuan untuk menampung nilai dan  tidak masuk dalam database.
	@Transient
	private int itemStock;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> listItem;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<Item> getListItem() {
		return listItem;
	}
	public void setListItem(List<Item> listItem) {
		this.listItem = listItem;
	}
	public int getItemStock() {
		return itemStock;
	}
	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}
	
	//updated
	
}
