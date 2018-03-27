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

import com.bootcamp.miniproject.model.Category;
import com.bootcamp.miniproject.model.Item;
import com.bootcamp.miniproject.service.CategoryService;
import com.bootcamp.miniproject.service.ItemService;

@Controller
@RequestMapping("master/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	@RequestMapping
	public String index(Model model) {
		List<Category> category = categoryService.selectAll();
		model.addAttribute("categorys", category);
		return "category";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String indexMainSearch(Model model, @RequestParam(value="search", defaultValue="") String search) {
		List<Category> categories = categoryService.getCategoryBySearchName(search);
		model.addAttribute("categorys", categories);
		return "category";
	}
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Category category) {
		categoryService.save(category);
	}
	
	@RequestMapping(value="/get-one/{id}")
	@ResponseBody
	public Category getOne(@PathVariable long id) {
		return categoryService.getOne(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody Category category) {
		categoryService.update(category);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		Category category = new Category();
		category.setId(id);
		categoryService.delete(category);
		return "redirect:/category";
	}
	
	
}
