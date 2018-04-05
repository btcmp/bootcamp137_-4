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
@Table(name = "mp_mst_item")
public class Item {
	
	public Item() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	private String name;
	
	@Column(name = "created_by")
	private User createdBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "modified_by")
	private User modifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modified_on")
	private Date modifiedOn;
	
	@NotNull
	private boolean active;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="item", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemVariant> itemVariant;
	
	
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

//	public long getCategoryId() {
//		return categoryId;
//	}
//
//	public void setCategoryId(long categoryId) {
//		this.categoryId = categoryId;
//	}

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

	public boolean isStatus() {
		return active;
	}

	public void setStatus(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ItemVariant> getItemVariant() {
		return itemVariant;
	}

	public void setItemVariant(List<ItemVariant> itemVariant) {
		this.itemVariant = itemVariant;
	}
	//updated
}
