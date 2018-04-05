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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseOrderDetail;
import com.bootcamp.miniproject.model.PurchaseOrderHistory;
import com.bootcamp.miniproject.model.PurchaseRequestHistory;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.PurchaseOrderDetailService;
import com.bootcamp.miniproject.service.PurchaseOrderHistoryService;
import com.bootcamp.miniproject.service.PurchaseOrderService;
import com.bootcamp.miniproject.service.SupplierService;

@Controller
@RequestMapping("/transaction/purchase-order")
public class PurchaseOrderController {
	
	@Autowired
	PurchaseOrderService poService;
	
	@Autowired
	PurchaseOrderDetailService poDetailService;
	
	@Autowired
	OutletService outletService;
	
	@Autowired
	SupplierService supplierService;
	@Autowired
	PurchaseOrderHistoryService poHistoryService;
	
	@Autowired
	ItemInventoryService inventoryService;
	@RequestMapping
	public String home(Model model) {
		model.addAttribute("outlets", outletService.selectAll());
		model.addAttribute("suppliers", supplierService.selectAll());
		return "purchase-order";
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseOrder> getAll(){
		return (List<PurchaseOrder>) poService.getAll();
	}
	
	@RequestMapping(value = "/getAllPo", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseOrder> getAllPoByOutlet(@RequestParam(value="outletId", defaultValue="") Long outletId){
		return (List<PurchaseOrder>) poService.getAllPoByOutlet(outletId);
	}
	
	@RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
	@ResponseBody
	public PurchaseOrder getOne(@PathVariable long id) {
		return poService.getOne(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody PurchaseOrder pr) {
		poService.update(pr);
	}
	
	@RequestMapping(value = "/search-item", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> searchItem(@RequestParam(value="outletId", defaultValue="") Long outletId,@RequestParam(value="name", defaultValue="") String search) {
		List<ItemInventory> itemsInventory = inventoryService.searchItemInventoryByItemNameAndOutlet(outletId, search);
		return itemsInventory;
	}
	
	@RequestMapping(value = "/get-item/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ItemInventory getOneItem(@PathVariable long id) {
		ItemInventory itemInventory = inventoryService.getOne(id);
		return itemInventory;
	}
	
	@RequestMapping(value = "/findPODetailAndQty", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> findPODetailAndQty(@RequestParam(value="outletId", defaultValue="") Long outletId,@RequestParam(value="poId", defaultValue="") Long poId) {
		return poDetailService.findPODetailAndQty(outletId, poId);
	}
	
	@RequestMapping(value="/approve/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void approve(@PathVariable long id) {
		poService.approve(id);
	}
	
	@RequestMapping(value="/reject/{id}", method= RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void reject(@PathVariable long id) {
		poService.reject(id);
	}
	@RequestMapping(value="/process/{id}", method= RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void process(@PathVariable long id) {
		poService.process(id);
	}
	@RequestMapping(value="/poDetail", method= RequestMethod.GET)
	@ResponseBody
	public List<PurchaseOrderDetail> getPoDetail(@RequestParam(value="poId", defaultValue="") Long poId) {
		return poService.getPODetailByPOIdandOutletId(poId);
	}
	@RequestMapping(value = "/getHistory/{poId}", method=RequestMethod.GET)
	@ResponseBody
	public List<PurchaseOrderHistory> getPRHistoryByPr(@PathVariable Long poId){
		return  poHistoryService.getPOHistoryByPo(poId);
	}
}
