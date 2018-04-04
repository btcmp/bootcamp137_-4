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

import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.Item;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.model.TransferStockDetail;
import com.bootcamp.miniproject.model.TransferStockHistory;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.TransferStockDetailService;
import com.bootcamp.miniproject.service.TransferStockHistoryService;
import com.bootcamp.miniproject.service.TransferStockService;

@Controller
@RequestMapping("/transaction/transfer-stock")
public class TransferStockController {

	@Autowired
	HttpSession httpSession;
	
	@Autowired
	TransferStockService transferStockService;
	
	@Autowired
	TransferStockDetailService transferStockDetailService;
	
	@Autowired
	ItemInventoryService itemInventoryService;
	
	@Autowired
	OutletService outletService;
	
	@Autowired
	TransferStockHistoryService transferStockHistoryService;
	
	@RequestMapping
	public String index(Model model) {
		Outlet outlet = (Outlet)httpSession.getAttribute("outlet");
		long outletId = outlet.getId();
		List<TransferStock> transferStocks = transferStockService.getTransferStockByFromOutletId(outletId);
		List<Outlet> outlets = outletService.selectAll();
		List<ItemInventory> itemsInventorys= itemInventoryService.getAll();
		model.addAttribute("transferStocks", transferStocks);
		model.addAttribute("outlets", outlets);
		model.addAttribute("itemInventorys", itemsInventorys);
		return "transferStock";
	}
	
	@RequestMapping(value = "/detail")
	public String indexDetail(Model model) {
		List<TransferStock> transferStocks = transferStockService.selectAll();
		model.addAttribute("transferStocks", transferStocks);
		return "transfer-stock-detail";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void saveTransferStock(@RequestBody TransferStock transferStock) {
		transferStockService.save(transferStock);
	}
	
	@RequestMapping(value = "/update-status/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateStatus(@RequestBody String newStatus, @PathVariable long id) {
		TransferStock transferStock = transferStockService.getOne(id);
		transferStock.setStatus(newStatus);
		transferStockService.saveUpdate(transferStock);
	}
	
	@RequestMapping(value = "/update-status-and-stock/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateStatusAndStock(@RequestBody String newStatus, @PathVariable long id) {
		TransferStock transferStock = transferStockService.getOne(id);
		transferStock.setStatus(newStatus);
		transferStockService.saveUpdateStatusAndStock(transferStock);
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
	
	@RequestMapping(value = "/search-outlet", method = RequestMethod.GET)
	public String indexMainSearch(Model model, @RequestParam(value="search", defaultValue="") long search) {
		List<TransferStock> transferStocks = transferStockService.getTransferStockByOutletId(search);
		List<Outlet> outlets = outletService.selectAll();
		List<ItemInventory> itemsInventorys= itemInventoryService.getAll();
		model.addAttribute("transferStocks", transferStocks);
		model.addAttribute("outlets", outlets);
		model.addAttribute("itemInventorys", itemsInventorys);
		return "transferStock";
	}
	
	@RequestMapping(value = "/search-item", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> searchItem(@RequestParam(value="search", defaultValue="") String search) {
		Outlet outlet = (Outlet) httpSession.getAttribute("outlet");
		List<ItemInventory> itemsInventory = itemInventoryService.searchItemInventoryByItemName(search);
		List<ItemInventory> itemInvent = new ArrayList<>();
		if (itemsInventory != null) {
			for(ItemInventory invent : itemsInventory) {
				if (invent.getOutlet().getId() == outlet.getId()) {
					itemInvent.add(invent);
				}
			}
		}
		return itemInvent;
	}
	
	@RequestMapping(value = "/get-one-item/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ItemInventory getOneItem(@PathVariable long id) {
		ItemInventory itemInventory = itemInventoryService.getOne(id);
		return itemInventory;
	}
	
	@RequestMapping(value = "/search-transfer-stock-detail", method = RequestMethod.GET)
	@ResponseBody
	public List<TransferStockDetail> searchTSDByTSID(@RequestParam(value="search", defaultValue="") long search) {
		List<TransferStockDetail> transferStockDetails = transferStockDetailService.getTransferStockDetailsByTransferStockId(search);
		return transferStockDetails;
	}
	
	@RequestMapping(value = "/search-transfer-stock-history", method = RequestMethod.GET)
	@ResponseBody
	public List<TransferStockHistory> searchTSHByTSID(@RequestParam(value="search", defaultValue="") long search) {
		List<TransferStockHistory> transferStockDetails = transferStockHistoryService.getTransferStockHistorysByTransferStockId(search);
		return transferStockDetails;
	}
}
