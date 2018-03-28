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

import com.bootcamp.miniproject.model.Role;
import com.bootcamp.miniproject.model.User;
import com.bootcamp.miniproject.service.RoleService;
import com.bootcamp.miniproject.service.UserService;

@Controller
@RequestMapping("master/user")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping
	public String index(Model model) {
		List<User> user = userService.selectAll();
		model.addAttribute("users", user);
		return "user";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody User user) {
		userService.save(user);
	}
	
	@RequestMapping(value="/get-one/{id}")
	@ResponseBody
	public User getOne(@PathVariable long id) {
		return userService.getOne(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody User user) {
		userService.update(user);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		User user = new User();
		user.setId(id);
		userService.delete(user);
		return "redirect:/user";
	}
}
