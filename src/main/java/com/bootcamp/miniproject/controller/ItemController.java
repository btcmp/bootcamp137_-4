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
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.miniproject.model.Item;
import com.bootcamp.miniproject.service.ItemService;

@Controller
@RequestMapping("/master/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping
	public String home(Model model) {
		model.addAttribute("items", itemService.getAll());
		return "item";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Item item) {
		itemService.save(item);
	}
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Item> getAll(){
		return (List<Item>) itemService.getAll();
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
}