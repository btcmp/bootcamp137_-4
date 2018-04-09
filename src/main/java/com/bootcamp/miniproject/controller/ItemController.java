package com.bootcamp.miniproject.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.miniproject.model.Item;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.ItemVariant;
import com.bootcamp.miniproject.model.User;
import com.bootcamp.miniproject.service.CategoryService;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.ItemService;
import com.bootcamp.miniproject.service.ItemVariantService;
import com.bootcamp.miniproject.service.OutletService;

@Controller
@RequestMapping("/master/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemVariantService variantService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	OutletService outletService;
	
	@Autowired
	ItemInventoryService inventoryService;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping
	public String home(Model model) {
		model.addAttribute("items", itemService.getAll());
		model.addAttribute("variant", variantService.getAll());
		model.addAttribute("categories", categoryService.selectAll());
		model.addAttribute("outlets", outletService.selectAll());
		model.addAttribute("inventories", inventoryService.getAll());
		User user = (User) httpSession.getAttribute("userLogin");
		return "item";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Item item) {
		itemService.save(item);
	}
	@RequestMapping(value = "/getAllVariants", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemVariant> getAllVariants(){
		return (List<ItemVariant>) variantService.getAll();
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Item> getAll(){
		return (List<Item>) itemService.getAll();
	}
	
	@RequestMapping(value = "/get-all-item-inventories", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> getAllInventories(){
		return (List<ItemInventory>) inventoryService.getAll();
	}
	
	@RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Item getOne(@PathVariable long id) {
		Item item = itemService.getOne(id);
		return item;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Item item) {
		itemService.update(item);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable long id) {
		Item item = itemService.getOne(id);
		System.out.println(item.getName());
		itemService.delete(item);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String indexBySearch(@RequestParam ("search") String search, Model model) {
		System.out.println(search);
		List<Item> items = itemService.getItemBySearchName(search);
		model.addAttribute("listItem", items);
		return "item";
	}
	@RequestMapping(value = "/deleteVariant/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void deleteVariant(@PathVariable long id) {
		 variantService.deleteVar(id);
	}
	
	@RequestMapping(value = "/searchInventory/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> getInvetoryByItemId(@PathVariable long id) {
		return inventoryService.getInventoryByItemId(id);
	}
	
	@RequestMapping(value = "/getItemInventory", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> getInventoryByItemIdandOutletId(@RequestParam ("itemId") Long id, @RequestParam ("outletId") Long outletId) {
		return inventoryService.getInventoryByItemIdandOutletId(id, outletId);
	}
	@RequestMapping(value = "/getInventory", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> getInventoryOutletId(@RequestParam ("outletId") Long outletId) {
		return inventoryService.getInventoryandOutletId(outletId);
	}
}