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

import com.bootcamp.miniproject.model.Item;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.TransferStockService;

@Controller
@RequestMapping("/transaction/transfer")
public class TransferStockController {

	@Autowired
	TransferStockService transferStockService;
	
	@Autowired
	ItemInventoryService itemInventoryService;
	
	@Autowired
	OutletService outletService;
	
	@RequestMapping
	public String index(Model model) {
		List<TransferStock> transferStocks = transferStockService.selectAll();
		List<Outlet> outlets = outletService.selectAll();
		List<ItemInventory> itemsInventorys= itemInventoryService.getAll();
		model.addAttribute("transferStocks", transferStocks);
		model.addAttribute("outlets", outlets);
		model.addAttribute("itemInventorys", itemsInventorys);
		return "transferStock";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void saveTransferStock(@RequestBody TransferStock transferStock) {
		transferStockService.save(transferStock);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateTransferStock(@RequestBody TransferStock transferStock) {
		transferStockService.saveUpdate(transferStock);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteTransferStock(@PathVariable long id) {
		TransferStock transferStock = new TransferStock();
		transferStock.setId(id);
		transferStockService.delete(transferStock);
	}
	
	@RequestMapping(value = "/get-one/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TransferStock getOneTransferStock(@PathVariable long id) {
		TransferStock transferStock = transferStockService.getOne(id);
		return transferStock;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String indexMainSearch(Model model, @RequestParam(value="search", defaultValue="") String search) {
		List<TransferStock> transferStocks = transferStockService.getTransferStockByOutletId(search);
		model.addAttribute("transferStocks", transferStocks);
		return "transferStock";
	}
	
	@RequestMapping(value = "/search-item", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> searchItem(@RequestParam(value="search", defaultValue="") String search) {
		List<ItemInventory> itemsInventory = itemInventoryService.searchItemInventoryByItemName(search);
		System.out.println(itemsInventory);
		return itemsInventory;
	}
	
	@RequestMapping(value = "/get-one-item/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ItemInventory getOneItem(@PathVariable long id) {
		ItemInventory itemInventory = itemInventoryService.getOne(id);
		return itemInventory;
	}
}
