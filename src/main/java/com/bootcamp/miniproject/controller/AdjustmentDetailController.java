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

import com.bootcamp.miniproject.model.AdjustmentDetail;
import com.bootcamp.miniproject.service.AdjustmentDetailService;

@Controller
@RequestMapping("transaction/adjustment-detail")
public class AdjustmentDetailController {

	@Autowired
	AdjustmentDetailService adjustmentDetailService;
	
	@RequestMapping
	public String index (Model model) {
		List<AdjustmentDetail> adjustmentDetail = adjustmentDetailService.selectAll();
		model.addAttribute("adjustmentDetails", adjustmentDetail);
		return "adjustment-detail";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody AdjustmentDetail adjustmentDetail) {
		adjustmentDetailService.save(adjustmentDetail);
	}
	
	@RequestMapping(value="/get-one/{id}", method=RequestMethod.GET)
	@ResponseBody
	public AdjustmentDetail getOne(@PathVariable long id) {
		return adjustmentDetailService.getOne(id);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody AdjustmentDetail adjustmentDetail) {
		adjustmentDetailService.update(adjustmentDetail);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		AdjustmentDetail adjustmentDetail = new AdjustmentDetail();
		adjustmentDetail.setId(id);
		adjustmentDetailService.delete(adjustmentDetail);
		return "redirect:/adjustment-detail";
	}
	
}
