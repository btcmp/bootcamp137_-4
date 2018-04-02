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

import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.Role;
import com.bootcamp.miniproject.service.EmployeeOutletService;
import com.bootcamp.miniproject.service.EmployeeService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.RoleService;

@Controller
@RequestMapping("master/employee")
public class EmployeeController {
//
	@Autowired
	EmployeeService employeeService;
	@Autowired
	RoleService roleService;
	@Autowired
	OutletService outletService;
	
	
	@RequestMapping
	public String index(Model model) {
		List<Employee> employee = employeeService.selectStatusActive();
		model.addAttribute("employees", employee);
		List<Role> role = roleService.selectAll();
		model.addAttribute("roles", role);
		List<Outlet> outlet = outletService.selectStatusActive();
		model.addAttribute("outlets", outlet);
		return "employee";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Employee employee) {
		employeeService.save(employee);
	}
	
	@RequestMapping(value="get-one/{id}")
	@ResponseBody
	public Employee getOne(@PathVariable long id) {
		return employeeService.getOne(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody Employee employee) {
		employeeService.update(employee);
	}
	
	@RequestMapping(value="/update-delete", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void updateDelete(@RequestBody Employee employee) {
		employeeService.updateDelete(employee);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		Employee employee = new Employee();
		employee.setId(id);
		employeeService.delete(employee);
		return "redirect:/employee";
	}
	
	@RequestMapping("/get-all")
	@ResponseBody
	public List<Employee> getAll(){
		return employeeService.selectAll();
	}
	
	
}
