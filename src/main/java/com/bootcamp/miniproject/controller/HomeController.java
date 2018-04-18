package com.bootcamp.miniproject.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bootcamp.miniproject.model.Adjustment;
import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.service.AdjustmentService;
import com.bootcamp.miniproject.service.PurchaseOrderService;
import com.bootcamp.miniproject.service.PurchaseRequestService;
import com.bootcamp.miniproject.service.TransferStockService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	TransferStockService transferStockService;
	
	@Autowired
	AdjustmentService adjustmentService;
	
	@Autowired
	PurchaseRequestService purchaseRequestService;

	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		Employee emp = (Employee) httpSession.getAttribute("employee");
		if (emp != null) {
			Outlet outlet =  (Outlet)httpSession.getAttribute("outlet");
			long id = outlet.getId();
			int jmlTs = 0 ;
			int jmlAdj = 0 ;
			int jmlPr = 0 ;
			int jmlPo = 0 ;
			
			List<TransferStock> transferStocks = transferStockService.getTransferStockByFromOutletId(id);
			if (transferStocks!=null) {
				for(TransferStock ts : transferStocks) {
					if (ts.getStatus().equals("Submitted")) {
						jmlTs++;
					}
				}
			}
			
			List<Adjustment> adjustments = adjustmentService.getAdjustmentIdByOutletId(id);
			if (adjustments!=null) {
				for(Adjustment adj : adjustments) {
					if (adj.getStatus().equals("Submitted")) {
						jmlAdj++;
					}
				}
			}
			
			List<PurchaseRequest> purchaseRequest = purchaseRequestService.getAllPrByOutlet(id);
			if (purchaseRequest!=null) {
				for(PurchaseRequest pr : purchaseRequest) {
					if (pr.getStatus().equals("Submitted")) {
						jmlPr++;
					}
				}
			}

			List<PurchaseOrder> purchaseOrder = purchaseOrderService.getAllPoByOutlet(id);
			if (purchaseOrder!=null) {
				for(PurchaseOrder po : purchaseOrder) {
					if (po.getStatus().equals("Submitted")) {
						jmlPo++;
					}
				}
			}
			
			model.addAttribute("jmlTs", jmlTs);
			model.addAttribute("jmlAdj", jmlAdj);
			model.addAttribute("jmlPr", jmlPr);
			model.addAttribute("jmlPo", jmlPo);
			return "main-menu";
		} else {
			return "login";
		}
	}
	
	@RequestMapping(value="/main-menu")
	public String home(Model model) {
		Outlet outlet =  (Outlet)httpSession.getAttribute("outlet");
		long id = outlet.getId();
		int jmlTs = 0 ;
		int jmlAdj = 0 ;
		int jmlPr = 0 ;
		int jmlPo = 0 ;
		
		List<TransferStock> transferStocks = transferStockService.getTransferStockByFromOutletId(id);
		if (transferStocks!=null) {
			for(TransferStock ts : transferStocks) {
				if (ts.getStatus().equals("Submitted")) {
					jmlTs++;
				}
			}
		}
		
		List<Adjustment> adjustments = adjustmentService.getAdjustmentIdByOutletId(id);
		if (adjustments!=null) {
			for(Adjustment adj : adjustments) {
				if (adj.getStatus().equals("Submitted")) {
					jmlAdj++;
				}
			}
		}
		
		List<PurchaseRequest> purchaseRequest = purchaseRequestService.getAllPrByOutlet(id);
		if (purchaseRequest!=null) {
			for(PurchaseRequest pr : purchaseRequest) {
				if (pr.getStatus().equals("Submitted")) {
					jmlPr++;
				}
			}
		}

		List<PurchaseOrder> purchaseOrder = purchaseOrderService.getAllPoByOutlet(id);
		if (purchaseOrder!=null) {
			for(PurchaseOrder po : purchaseOrder) {
				if (po.getStatus().equals("Submitted")) {
					jmlPo++;
				}
			}
		}
		
		model.addAttribute("jmlTs", jmlTs);
		model.addAttribute("jmlAdj", jmlAdj);
		model.addAttribute("jmlPr", jmlPr);
		model.addAttribute("jmlPo", jmlPo);
		return "main-menu";
	}
	
}
