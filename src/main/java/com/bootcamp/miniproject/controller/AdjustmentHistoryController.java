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

import com.bootcamp.miniproject.model.AdjustmentHistory;
import com.bootcamp.miniproject.service.AdjustmentHistoryService;

@Controller
@RequestMapping("/transaction/adjustment-history")
public class AdjustmentHistoryController {

	@Autowired
	AdjustmentHistoryService adjustmentHistoryService;
	
	@RequestMapping
	public String index(Model model) {
		List<AdjustmentHistory> adjustmentHistory = adjustmentHistoryService.selectAll();
		model.addAttribute("adjustmentHistories", adjustmentHistory);
		return "adjustment-history";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody AdjustmentHistory adjustmentHistory) {
		adjustmentHistoryService.save(adjustmentHistory);
	}
	
	@RequestMapping(value="/get-one/{id}", method=RequestMethod.GET)
	@ResponseBody
	public AdjustmentHistory getOne(@PathVariable long id) {
		return adjustmentHistoryService.getOne(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody AdjustmentHistory adjustmentHistory) {
		adjustmentHistoryService.update(adjustmentHistory);
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		AdjustmentHistory adjustmentHistory = new AdjustmentHistory();
		adjustmentHistory.setId(id);
		adjustmentHistoryService.delete(adjustmentHistory);
		return "redirect:/adjustment-history";
	}
	
	
}
