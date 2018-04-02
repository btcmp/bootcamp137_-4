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

import com.bootcamp.miniproject.model.Adjustment;
import com.bootcamp.miniproject.model.AdjustmentDetail;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.service.AdjustmentDetailService;
import com.bootcamp.miniproject.service.AdjustmentService;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.OutletService;

@Controller 
@RequestMapping("transaction/adjustment")
public class AdjustmentController {
	
	@Autowired
	AdjustmentService adjustmentService;
	
	@Autowired
	AdjustmentDetailService adjustmentDetailService;
	
	@Autowired
	OutletService outletService;
	
	@Autowired
	ItemInventoryService itemInventoryService;
	
	
	@RequestMapping
	public String index(Model model) {
		List<Adjustment> adjustment = adjustmentService.selectAll();
		List<Outlet> outlet = outletService.selectStatusActive();
		List<ItemInventory> itemInventory = itemInventoryService.getAll();
		model.addAttribute("adjustments", adjustment);
		model.addAttribute("outlets",outlet);
		model.addAttribute("itemInventories",itemInventory);
		return "adjustment";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Adjustment adjustment) {
		adjustmentService.save(adjustment);
	}
	
	@RequestMapping(value="/get-one/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Adjustment getOne(@PathVariable long id) {
		return adjustmentService.getOne(id);
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
		List<ItemInventory> itemsInventory = itemInventoryService.searchItemInventoryByItemName(search);
		return itemsInventory;
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody Adjustment adjustment) {
		adjustmentService.update(adjustment);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		Adjustment adjustment = new Adjustment();
		adjustment.setId(id);
		adjustmentService.delete(adjustment);
		return "redirect:/adjustment";
	}

}
