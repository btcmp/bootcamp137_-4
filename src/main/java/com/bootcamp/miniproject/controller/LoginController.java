package com.bootcamp.miniproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping
	public String doLogin(Model model, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout) {
		if(error != null){
			model.addAttribute("error", "user or password invalid..!!");
		} 
		if(logout != null){
			model.addAttribute("logout", "you have been logged out..!!");
		}
		
		return "login";
	}
	
	@RequestMapping("/index") 
	public String index() {
		return "home";
	}
	
	@RequestMapping("/access-denied") 
	public String accessDenied() {
		return "access-denied";
	}
	//
}
