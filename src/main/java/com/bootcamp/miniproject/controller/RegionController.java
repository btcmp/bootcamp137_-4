package com.bootcamp.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.miniproject.model.Region;
import com.bootcamp.miniproject.service.RegionService;

@Controller
@RequestMapping("/additional/region")
public class RegionController {

	@Autowired
	RegionService regionService;
	
	@RequestMapping(value="/get-region", method=RequestMethod.GET)
	@ResponseBody
	public List<Region> getRegionByProvinceId(Model model, @RequestParam(value="id", defaultValue="") long id) {
		List<Region> regions = regionService.getRegionByProvinceId(id);
		return regions;
	}
}
