package com.bootcamp.miniproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bootcamp.miniproject.model.Customer;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.Province;
import com.bootcamp.miniproject.model.SalesOrder;
import com.bootcamp.miniproject.model.SalesOrderDetail;
import com.bootcamp.miniproject.service.CustomerService;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.ProvinceService;
import com.bootcamp.miniproject.service.SalesOrderService;

@Controller
@RequestMapping("/transaction/sales-order")
public class SalesOrderController {

	@Autowired
	HttpSession httpSession;
	
	@Autowired
	SalesOrderService salesOrderService;
	
	@Autowired
	ItemInventoryService itemInventoryService;
	
	@Autowired
	ProvinceService provinceService;
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping
	public String index(Model model) {
		List<SalesOrder> salesOrders = salesOrderService.selectAll();
		List<Province> provinces = provinceService.selectAll();
		model.addAttribute("salesOrders", salesOrders);
		model.addAttribute("provinces", provinces);
		return "sales-order";
	}
	
	@RequestMapping(value = "/get-all", method = RequestMethod.GET)
	@ResponseBody
	public List<SalesOrder> getAll() {
		List<SalesOrder> salesOrder = salesOrderService.selectAll();
		return salesOrder;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void saveSalesOrder(@RequestBody SalesOrder salesOrder) {
		salesOrderService.save(salesOrder);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateSalesOrder(@RequestBody SalesOrder salesOrder) {
		salesOrderService.saveUpdate(salesOrder);
	}
	
	@RequestMapping(value = "/update-stock", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateStock(@RequestBody SalesOrder salesOrder) {
		salesOrderService.updateStock(salesOrder);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteSalesOrder(@PathVariable long id) {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setId(id);
		salesOrderService.delete(salesOrder);
	}
	
	@RequestMapping(value = "/get-one/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SalesOrder getOneSalesOrder(@PathVariable long id) {
		SalesOrder salesOrder = salesOrderService.getOne(id);
		return salesOrder;
	}
	
	@RequestMapping(value = "/get-one-item/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ItemInventory getOneItem(@PathVariable long id) {
		ItemInventory itemInventory = itemInventoryService.getOne(id);
		return itemInventory;
	}
	
	@RequestMapping(value = "/search-item", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> searchItem(@RequestParam(value="search", defaultValue="") String search) {
		Outlet outlet = (Outlet) httpSession.getAttribute("outlet");
		List<ItemInventory> itemsInventory = itemInventoryService.searchItemInventoryByItemNameAndOutlet(outlet.getId(), search);
		return itemsInventory;
	}
	
	@RequestMapping(value = "/search-customer", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> searchCustomer(@RequestParam(value="search", defaultValue="") String search) {
		List<Customer> customers = customerService.getCustomerBySearchName(search);
		return customers;
	}
	
	@RequestMapping(value = "/get-sod", method = RequestMethod.GET)
	@ResponseBody
	public List<SalesOrderDetail> getSODBySOId(@RequestParam(value="search", defaultValue="") long id){
		List<SalesOrderDetail> sod = salesOrderService.getSODBySOId(id);
		return sod;
	}
}
