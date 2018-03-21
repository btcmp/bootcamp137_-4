package com.bootcamp.miniproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mp_mst_employee_outlet")
public class EmployeeOutlet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
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
