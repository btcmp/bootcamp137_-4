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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mp_mst_item_variant")
public class ItemVariant {
	
	public ItemVariant() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@NotNull
	private String name;
	@NotNull
	private String sku;
	@NotNull
	private float price;
	
	@Column(name = "category_id")
	private long categoryId;
	
	@Column(name = "created_by")
	private long createdBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "modified_by")
	private long modifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modified_on")
	private Date modifiedOn;
	
	@NotNull
	private boolean active;
	
	@ManyToOne
	private Item item;
	
//	@OneToMany(fetch= FetchType.LAZY, mappedBy="itemVariant", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<PurchaseRequestDetail> purchaseRequestDetail;
//	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="itemVariant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemInventory> itemInventory;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<ItemInventory> getItemInventory() {
		return itemInventory;
	}

	public void setItemInventory(List<ItemInventory> itemInventory) {
		this.itemInventory = itemInventory;
	}

	

//	public List<PurchaseRequestDetail> getPurchaseRequestDetail() {
//		return purchaseRequestDetail;
//	}
//
//	public void setPurchaseRequestDetail(List<PurchaseRequestDetail> purchaseRequestDetail) {
//		this.purchaseRequestDetail = purchaseRequestDetail;
//	}
//	
//	
	//updated
}
