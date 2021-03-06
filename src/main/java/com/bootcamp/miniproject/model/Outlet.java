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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

import com.sun.istack.NotNull;

@Entity
@Table(name="mp_mst_outlet")
public class Outlet {
	
	public Outlet() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	
	@NotNull
	private String name;
	private String address;
	private String phone;
	
	@Email
	private String email;
	
	@ManyToOne
	private Province province;
	@ManyToOne
	private Region region;
	@ManyToOne
	private District district;
	
	@Column(name="postal_code")
	private String postalCode;
	
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
	
	@NotNull
	private boolean active;
	
	@OneToMany (fetch=FetchType.LAZY, mappedBy="outlet", cascade=CascadeType.ALL, orphanRemoval=true)
	List<EmployeeOutlet> employeeOutlets;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="outlet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemInventory> listItemInventory;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="outlet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseRequest> purchaseRequest;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="outlet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseOrder> purchaseDetail;
	
	
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public List<EmployeeOutlet> getEmployeeOutlets() {
		return employeeOutlets;
	}
	public void setEmployeeOutlets(List<EmployeeOutlet> employeeOutlets) {
		this.employeeOutlets = employeeOutlets;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<ItemInventory> getListItemInventory() {
		return listItemInventory;
	}
	public void setListItemInventory(List<ItemInventory> listItemInventory) {
		this.listItemInventory = listItemInventory;
	}
	public List<PurchaseRequest> getPurchaseRequest() {
		return purchaseRequest;
	}
	public void setPurchaseRequest(List<PurchaseRequest> purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}
	public List<PurchaseOrder> getPurchaseDetail() {
		return purchaseDetail;
	}
	public void setPurchaseDetail(List<PurchaseOrder> purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
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
