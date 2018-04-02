package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.EmployeeOutlet;

public interface EmployeeOutletDao {

	public void save(EmployeeOutlet employeeOutlet);
	public void saveOrUpdate(EmployeeOutlet employeeOutlet);
	public List<EmployeeOutlet> selectAll();
	public EmployeeOutlet getOne(EmployeeOutlet employeeOutlet);
	public void update(EmployeeOutlet employeeOutlet);
	public void delete(EmployeeOutlet employeeOutlet);
	public List<EmployeeOutlet> getEmployeeOutletByEmployee(Employee employee);
	public void deleteByEmployeeId(long employeeId);
}
