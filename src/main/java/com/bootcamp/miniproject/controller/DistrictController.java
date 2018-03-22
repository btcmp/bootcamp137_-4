package com.bootcamp.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.miniproject.model.District;
import com.bootcamp.miniproject.service.DistrictService;

@Controller
@RequestMapping("/additional/district")
public class DistrictController {

	@Autowired
	DistrictService districtService;
	
	@RequestMapping(value="/get-district", method=RequestMethod.GET)
	@ResponseBody
	public List<District> getDistrictByRegionId(Model model, @RequestParam(value="id", defaultValue="") long id) {
		List<District> districts = districtService.getDistrictByRegionId(id);
		return districts;
	}
}
