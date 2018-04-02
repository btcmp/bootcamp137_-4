package com.bootcamp.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.PurchaseOrderService;
import com.bootcamp.miniproject.service.SupplierService;

@Controller
@RequestMapping("/transaction/purchase-order")
public class PurchaseOrderController {
	
	@Autowired
	PurchaseOrderService poService;
	
	@Autowired
	OutletService outletService;
	
	@Autowired
	SupplierService supplierService;
	
	@RequestMapping
	public String home(Model model) {
		model.addAttribute("outlets", outletService.selectAll());
		model.addAttribute("suppliers", supplierService.selectAll());
		return "purchase-order";
	}
}
