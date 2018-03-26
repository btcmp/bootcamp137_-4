package com.bootcamp.miniproject.dao;

import java.util.List;

import com.bootcamp.miniproject.model.Customer;

public interface CustomerDao {
	
	public void save(Customer customer);
	public List<Customer> selectAll();
	public Customer getOne(Customer customer);
	public void update(Customer customer);
	public void delete(Customer customer);
//	
}
