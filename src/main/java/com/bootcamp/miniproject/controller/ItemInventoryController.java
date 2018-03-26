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

import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.service.ItemInventoryService;

@Controller
@RequestMapping("/master/ItemInventory")
public class ItemInventoryController {
	@Autowired
	ItemInventoryService itemInventoryService;
	
	@RequestMapping
	@ResponseBody
	public String home(Model model) {
		model.addAttribute("items", itemInventoryService.getAll());
		return "item";
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> getAll(Model model) {
		model.addAttribute("items", itemInventoryService.getAll());
		return itemInventoryService.getAll();
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody ItemInventory itemInventory) {
		itemInventoryService.save(itemInventory);
	}
	@RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ItemInventory getOne(@PathVariable long id) {
		return itemInventoryService.getOne(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody ItemInventory itemInventory) {
		itemInventoryService.update(itemInventory);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable long id) {
		ItemInventory itemInventory = itemInventoryService.getOne(id);
		itemInventoryService.delete(itemInventory);
	}
}
