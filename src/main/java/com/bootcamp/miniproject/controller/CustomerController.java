package com.bootcamp.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bootcamp.miniproject.model.Customer;
import com.bootcamp.miniproject.service.CustomerService;

@Controller
@RequestMapping("master/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping
	public String index(Model model) {
		List<Customer> customers = customerService.selectAll();
		model.addAttribute("customers", customers);
		return "customer";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Customer customer) {
		customerService.save(customer);
	}
	
	@RequestMapping(value="/get-one/{id}")
	@ResponseBody
	public Customer getOne(@PathVariable long id) {
		return customerService.getOne(id);
	}
	
	@RequestMapping(value="/get-all")
	@ResponseBody
	public List<Customer> getAll() {
		return customerService.selectAll();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody Customer customer) {
		customerService.update(customer);
	}
	
	public String delete(@PathVariable long id) {
		Customer customer = new Customer();
		customer.setId(id);
		customerService.delete(customer);
		return "redirect:/customer";
	}
}
