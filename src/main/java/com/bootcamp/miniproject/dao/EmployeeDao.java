package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Employee;

public interface EmployeeDao {

	public void save(Employee employee);
	public List<Employee> selectAll();
	public Employee getOne(Employee employee);
	public void update(Employee employee);
	public void delete(Employee employee);
	public List<Employee> selectStatusActive();
	
}
