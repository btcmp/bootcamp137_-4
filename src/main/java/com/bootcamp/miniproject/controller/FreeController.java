package com.bootcamp.miniproject.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.EmployeeOutlet;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.service.EmployeeService;
import com.bootcamp.miniproject.service.OutletService;

@Controller
@RequestMapping("/free-autentication")
public class FreeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	OutletService outletService;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value="/get-one-by-username")
	@ResponseBody
	public List<Employee> getOneByUsername(@RequestParam(value="username", defaultValue="") String username) {
		return employeeService.getOneByUsername(username);
	}
	
	@RequestMapping(value="/choose-outlet")
	public String chooseOutlet(Model model, Principal principal) {
		String username = principal.getName();
		List<Employee> employee = employeeService.getOneByUsername(username);
		Employee empl = new Employee();
		for(Employee emp : employee) {
			empl = emp;
		}
		httpSession.setAttribute("username", username);
		httpSession.setAttribute("employee", empl);
		List<EmployeeOutlet> empOutlets = empl.getEmployeeOutlets();
		List<Outlet> outlets = new ArrayList<>();
		for(EmployeeOutlet eo : empOutlets) {
			Outlet out = eo.getOutlet();
			outlets.add(out);
		}
		System.out.println(empOutlets.size()+" "+outlets.size());
		model.addAttribute("outlets", outlets);
		return "choose-outlet";
	}
	
	@RequestMapping(value="/home")
	public String home(@RequestParam(value="id", defaultValue="") long id) {
		Outlet outlet =  outletService.getOne(id);
		httpSession.setAttribute("outlet", outlet);
		return "main-menu";
	}
	//
}
