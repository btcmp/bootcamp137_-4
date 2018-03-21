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

import com.bootcamp.miniproject.model.Supplier;
import com.bootcamp.miniproject.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	
	@RequestMapping
	public String index(Model model) {
		List<Supplier> suppliers = supplierService.selectAll();
		model.addAttribute("suppliers", suppliers);
		return "supplier";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void saveSupplier(@RequestBody Supplier supplier) {
		supplierService.save(supplier);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateSupplier(@RequestBody Supplier supplier) {
		supplierService.saveUpdate(supplier);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteSupplier(@PathVariable long id) {
		Supplier supplier = new Supplier();
		supplier.setId(id);
		supplierService.delete(supplier);
	}
	
	@RequestMapping(value = "/get-one/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Supplier getOneSupplier(@PathVariable long id) {
		Supplier supplier = supplierService.getOne(id);
		return supplier;
	}
}
