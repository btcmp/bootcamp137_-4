package com.bootcamp.miniproject.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

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

import com.bootcamp.miniproject.model.Adjustment;
import com.bootcamp.miniproject.model.AdjustmentDetail;
import com.bootcamp.miniproject.model.AdjustmentHistory;
import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.model.TransferStockHistory;
import com.bootcamp.miniproject.service.AdjustmentDetailService;
import com.bootcamp.miniproject.service.AdjustmentHistoryService;
import com.bootcamp.miniproject.service.AdjustmentService;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.OutletService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Controller 
@RequestMapping("transaction/adjustment")
public class AdjustmentController {
	
	@Autowired
	AdjustmentService adjustmentService;
	
	@Autowired
	AdjustmentDetailService adjustmentDetailService;
	
	@Autowired
	OutletService outletService;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	ItemInventoryService itemInventoryService;
	
	@Autowired 
	AdjustmentHistoryService adjustmentHistoryService;
	
	
	@RequestMapping
	public String index(Model model) {
		Outlet outlet = (Outlet) httpSession.getAttribute("outlet");
		long outletId = outlet.getId();
		List<Adjustment> adjustment = adjustmentService.getAdjustmentIdByOutletId(outletId);
		List<Outlet> outlets = outletService.selectStatusActive();
		List<ItemInventory> itemInventory = itemInventoryService.getAll();
		model.addAttribute("adjustments", adjustment);
		model.addAttribute("outlets",outlets);
		model.addAttribute("itemInventories",itemInventory);
		return "adjustment";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Adjustment adjustment) {
		adjustmentService.save(adjustment);
	}
	
	@RequestMapping(value="/get-one/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Adjustment getOne(@PathVariable long id) {
		return adjustmentService.getOne(id);
	}
	
	
	@RequestMapping(value = "/get-one-item/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ItemInventory getOneItem(@PathVariable long id) {
		ItemInventory itemInventory = itemInventoryService.getOne(id);
		return itemInventory;
	}
	
	@RequestMapping(value = "/search-item", method = RequestMethod.GET)
	@ResponseBody
	public List<ItemInventory> searchItem(@RequestParam(value="search", defaultValue="") String search) {
		Outlet outlet = (Outlet)httpSession.getAttribute("outlet");
		long outletId = outlet.getId();
		List<ItemInventory> itemsInventory = itemInventoryService.searchItemInventoryByItemNameAndOutlet(outletId, search);
		return itemsInventory;
	}
	
	@RequestMapping(value = "/search-adjustment-detail", method = RequestMethod.GET)
	@ResponseBody
	public List<AdjustmentDetail> searchAdjustmentDetailByAdjustmentId(@RequestParam(value="search", defaultValue="") long search) {
		List<AdjustmentDetail> adjustmentDetails = adjustmentDetailService.getAdjustmentDetailByAdjustmentId(search);
		return adjustmentDetails;
	}

	@RequestMapping(value = "/search-adjustment-history", method = RequestMethod.GET)
	@ResponseBody
	public List<AdjustmentHistory> searchAdjustmentHistoryByAdjustmentId(@RequestParam(value="search", defaultValue="") long search) {
		List<AdjustmentHistory> adjustmentHistories = adjustmentHistoryService.getAdjustmentHistoryByAdjustmentId(search);
		return adjustmentHistories;
	}
	
	@RequestMapping(value="/search-adjustment-byDateRange", method = RequestMethod.GET)
	public String searchAdjustmentByDateRange(Model model, @RequestParam(value="start", defaultValue="") String startString, @RequestParam(value="end", defaultValue="")String endString) throws ParseException {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		Date start = df.parse(startString);
		Date end = df.parse(endString);
		List<Adjustment> adjustments = adjustmentService.searchAdjustmentByDateRange(start, end);
		List<Outlet> outlets = outletService.selectStatusActive();
		List<ItemInventory> itemInventory = itemInventoryService.getAll();
		model.addAttribute("adjustments", adjustments);
		model.addAttribute("outlets",outlets);
		model.addAttribute("itemInventories",itemInventory);
		return "adjustment";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody Adjustment adjustment) {
		adjustmentService.update(adjustment);
	}
	
	@RequestMapping(value = "/update-status/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateStatus(@RequestBody String newStatus, @PathVariable long id) {
		Adjustment adjustment = adjustmentService.getOne(id);
		adjustment.setStatus(newStatus);
		adjustmentService.update(adjustment);
	}
	
	@RequestMapping(value = "/update-status-and-stock/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateStatusAndStock(@RequestBody String newStatus, @PathVariable long id) {
		Adjustment adjustment = adjustmentService.getOne(id);
		adjustment.setStatus(newStatus);
		adjustmentService.updateStatusAndStock(adjustment);
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		Adjustment adjustment = new Adjustment();
		adjustment.setId(id);
		adjustmentService.delete(adjustment);
		return "redirect:/adjustment";
	}

}
