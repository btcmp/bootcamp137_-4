package com.bootcamp.miniproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="mp_mst_user")
public class User {

	public User() {
		this.createdOn = new Date();
		this.modifiedOn = new Date();
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
		

	@ManyToOne
	@NotNull
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToOne
	@NotNull
	@JoinColumn(name="employee_id")
	@JsonBackReference
	private Employee employee;
	
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
	
	@NotNull
	private boolean active;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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
	
	
	
}