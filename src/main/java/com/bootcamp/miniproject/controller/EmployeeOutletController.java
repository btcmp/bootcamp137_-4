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

import com.bootcamp.miniproject.model.EmployeeOutlet;
import com.bootcamp.miniproject.service.EmployeeOutletService;

@Controller
@RequestMapping("master/employeeoutlet")
public class EmployeeOutletController {

	@Autowired
	EmployeeOutletService employeeOutletService;
	
	@RequestMapping
	public String index(Model model) {
		List<EmployeeOutlet> employeeOutlets = employeeOutletService.selectAll();
		model.addAttribute("employeeOutlets", employeeOutlets);
		return "employeeoutlet";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdate(@RequestBody EmployeeOutlet employeeOutlet) {
		employeeOutletService.saveOrUpdate(employeeOutlet);
	}
	
	@RequestMapping(value="get-one/{id}")
	@ResponseBody
	public EmployeeOutlet getOne(@PathVariable long id) {
		return employeeOutletService.getOne(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody EmployeeOutlet employeeOutlet) {
		employeeOutletService.update(employeeOutlet);
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		EmployeeOutlet employeeOutlet = new EmployeeOutlet();
		employeeOutlet.setId(id);
		employeeOutletService.delete(employeeOutlet);
		return "redirect:/employeeoutlet";
	}
	
}
