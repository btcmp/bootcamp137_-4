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

import com.bootcamp.miniproject.model.District;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.Province;
import com.bootcamp.miniproject.model.Region;
import com.bootcamp.miniproject.service.DistrictService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.ProvinceService;
import com.bootcamp.miniproject.service.RegionService;

@Controller
@RequestMapping("master/outlet")
public class OutletController {

	@Autowired
	OutletService outletService;
	
	@Autowired
	ProvinceService provinceService;
	
	@Autowired
	RegionService regionService;
	
	@Autowired
	DistrictService districtService;
	
	@RequestMapping
	public String index(Model model) {
		List<Outlet> outlets = outletService.selectStatusActive();
		List<Province> provinces = provinceService.selectAll();
		List<Region> regions = regionService.selectAll();
		List<District> districts = districtService.selectAll();
		model.addAttribute("outlets", outlets);
		model.addAttribute("provinces", provinces);
		model.addAttribute("regions", regions);
		model.addAttribute("districts", districts);
		return "outlet";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String indexMainSearch(Model model, @RequestParam(value="search", defaultValue="")String search) {
		List<Outlet> outlets = outletService.getOutletBySearchName(search);
		List<Province> provinces = provinceService.selectAll();
		List<Region> regions = regionService.selectAll();
		List<District> districts = districtService.selectAll();
		model.addAttribute("outlets",outlets);
		model.addAttribute("provinces", provinces);
		model.addAttribute("regions", regions);
		model.addAttribute("districts", districts);
		return "outlet";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Outlet outlet) {
		outletService.save(outlet);
	}
	
	@RequestMapping(value="/get-one/{id}")
	@ResponseBody
	public Outlet getOne(@PathVariable long id) {
		return outletService.getOne(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody Outlet outlet) {
		outletService.update(outlet);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		Outlet outlet = new Outlet();
		outlet.setId(id);
		outletService.delete(outlet);
		return "redirect:/outlet";
	}
	
	@RequestMapping(value="/get-all", method = RequestMethod.GET)
	@ResponseBody
	public List<Outlet> getAllOutlet() {
		List<Outlet> outlets = outletService.selectAll();
		return outlets;
	}
	
}

