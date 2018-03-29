package com.bootcamp.miniproject.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
	
@Entity
@Table(name="mp_mst_employee_outlet")
public class EmployeeOutlet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	@JsonIgnore
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="outlet_id")
	private Outlet outlet;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Outlet getOutlet() {
		return outlet;
	}

	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}
	
	

}
