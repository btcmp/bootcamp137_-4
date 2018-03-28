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
import com.bootcamp.miniproject.service.RoleService;

@Controller
@RequestMapping("master/role")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@RequestMapping
	public String index(Model model) {
		List<Role> role = roleService.selectAll();
		model.addAttribute("roles", role);
		return "role";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Role role) {
		roleService.save(role);
	}
	
	@RequestMapping(value="/get-one/{id}")
	@ResponseBody
	public Role getOne(@PathVariable long id) {
		return roleService.getOne(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody Role role) {
		roleService.update(role);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		Role role = new Role();
		role.setId(id);
		roleService.delete(role);
		return "redirect:/role";
	}
	

}

