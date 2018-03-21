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

import com.bootcamp.miniproject.model.ItemVariant;
import com.bootcamp.miniproject.service.ItemVariantService;

@Controller
@RequestMapping(value = "/master/itemVariantVariant")
public class ItemVariantController {
	@Autowired
	ItemVariantService itemVariantService;
	
	@RequestMapping
	public String home(Model model) {
		model.addAttribute("itemVariants", itemVariantService.getAll());
		return "itemVariant";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody ItemVariant itemVariant) {
		itemVariantService.save(itemVariant);
	}
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemVariant> getAll(){
		return (List<ItemVariant>) itemVariantService.getAll();
	}
	
	@RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ItemVariant getOne(@PathVariable long id) {
		ItemVariant itemVariant = itemVariantService.getOne(id);
		return itemVariant;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody ItemVariant itemVariant) {
		itemVariantService.update(itemVariant);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable long id) {
		ItemVariant itemVariant = itemVariantService.getOne(id);
		System.out.println(itemVariant.getName());
		itemVariantService.delete(itemVariant);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String indexBySearch(@RequestParam ("search") String search, Model model) {
		System.out.println(search);
		List<ItemVariant> itemVariants = itemVariantService.getItemVariantBySearchName(search);
		model.addAttribute("listItemVariant", itemVariants);
		return "itemVariant";
	}
}
