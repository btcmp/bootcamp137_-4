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
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.PurchaseRequestDetail;
import com.bootcamp.miniproject.model.PurchaseRequestHistory;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.PurchaseRequestDetailService;
import com.bootcamp.miniproject.service.PurchaseRequestHistoryService;
import com.bootcamp.miniproject.service.PurchaseRequestService;

@Controller
@RequestMapping("/transaction/purchase-request")
public class PurchaseRequestController {
	@Autowired
	PurchaseRequestService prService;
	
	@Autowired
	ItemInventoryService inventoryService;
	
	@Autowired
	PurchaseRequestDetailService prDetailService;
	
	@Autowired
	PurchaseRequestHistoryService prHistoryService;
	
	@Autowired
	OutletService outletService;
	@RequestMapping
	public String home(Model model) {
		model.addAttribute("purchaseRequest", prService.getAll());
		model.addAttribute("itemInventories", inventoryService.getAll());
		model.addAttribute("outlets", outletService.selectAll());
		return "purchase-request";
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseRequest> getAll(){
		return (List<PurchaseRequest>) prService.getAll();
	}
	
	@RequestMapping(value = "/getAllPr", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseRequest> getAllPrByOutlet(@RequestParam(value="outletId", defaultValue="") Long outletId){
		return (List<PurchaseRequest>) prService.getAllPrByOutlet(outletId);
	}
	
	@RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
	@ResponseBody
	public PurchaseRequest getOne(@PathVariable long id) {
		return prService.getOne(id);
	}
	@RequestMapping(value = "/getPRDetail", method = RequestMethod.GET)
	@ResponseBody
	public List<PurchaseRequestDetail> getPRDetailByPRIdandOutletId(@RequestParam(value="prId", defaultValue="") Long prId, @RequestParam(value="outledId", defaultValue="") Long outletId) {
		return prService.getPRDetailByPRIdandOutletId(prId,outletId);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody PurchaseRequest pr) {
		prService.save(pr);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody PurchaseRequest pr) {
		prService.update(pr);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable long id) {
		PurchaseRequest pr = prService.getOne(id);
		prService.delete(pr);
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
	
	@RequestMapping(value = "/findPRDetailAndQty", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> findPRDetailAndQty(@RequestParam(value="outletId", defaultValue="") Long outletId,@RequestParam(value="prId", defaultValue="") Long prId) {
		return prDetailService.findPRDetailAndQty(outletId, prId);
	}
	
	@RequestMapping(value="/approve/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void approve(@PathVariable long id) {
		prService.approve(id);
	}
	
	@RequestMapping(value="/reject/{id}", method= RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void reject(@PathVariable long id) {
		prService.reject(id);
	}
	
	@RequestMapping(value="/create-po/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void createPo(@PathVariable long id) {
		prService.createPo(id);
	}
	
	@RequestMapping("/search-status")
	@ResponseBody
	public List<PurchaseRequest> getByStatus(@RequestParam(value="outletId", defaultValue="") Long outletId, @RequestParam(value="status", defaultValue="") String status){
		return prService.getPRByStatus(outletId, status);
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public List<PurchaseRequest> search(@RequestParam(value="search", defaultValue="") String search){
		return prService.search(search);
	}
	
	@RequestMapping(value = "/getHistory/{prId}", method=RequestMethod.GET)
	@ResponseBody
	public List<PurchaseRequestHistory> getPRHistoryByPr(@PathVariable Long prId){
		return  prHistoryService.getPRHistoryByPr(prId);
	}
}
