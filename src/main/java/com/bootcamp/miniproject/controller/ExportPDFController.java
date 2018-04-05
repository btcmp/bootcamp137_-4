package com.bootcamp.miniproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.miniproject.model.Adjustment;
import com.bootcamp.miniproject.model.Category;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.SalesOrder;
import com.bootcamp.miniproject.model.Supplier;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.service.AdjustmentService;
import com.bootcamp.miniproject.service.CategoryService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.PurchaseRequestService;
import com.bootcamp.miniproject.service.SalesOrderService;
import com.bootcamp.miniproject.service.SupplierService;
import com.bootcamp.miniproject.service.TransferStockService;

@Controller
@RequestMapping("/generate")
public class ExportPDFController {

	@Autowired
	SupplierService supplierService;
	
	@Autowired
	PurchaseRequestService purchaseRequestService;
	
	@Autowired
	TransferStockService tsService;
	
	@Autowired
	OutletService outletService; 
	
	@Autowired
	CategoryService categoryService; 
	
	@Autowired
	SalesOrderService salesOrderService; 
	
	@Autowired
	AdjustmentService adjustmentService;
	
	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	ModelAndView generatePdf(HttpServletRequest request,
	HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"suppliers.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<Supplier> suppliers = supplierService.selectAll();

	return new ModelAndView("pdfViewSupplier","suppliers",suppliers);
 	}
	
	@RequestMapping(value = "/pr", method = RequestMethod.GET)
	ModelAndView generatePdfPR(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"purchase_request.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<PurchaseRequest> prs = purchaseRequestService.getAll();

	return new ModelAndView("pdfViewPR","prs",prs);
 	}
	
	@RequestMapping(value = "/ts", method = RequestMethod.GET)
	ModelAndView generatePdfs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"transfer_stock.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<TransferStock> tss = tsService.selectAll();

	return new ModelAndView("pdfViewTS","tss",tss);
 	}
	
	@RequestMapping(value = "/outlet", method = RequestMethod.GET)
	ModelAndView generatePdfOutlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"outlet.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<Outlet> outlets = outletService.selectAll();

	return new ModelAndView("pdfViewOutlet","outlets",outlets);
 	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	ModelAndView generatePdfCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"category.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<Category> categories = categoryService.selectAll();

	return new ModelAndView("pdfViewCategory","categories",categories);
 	}
	
	@RequestMapping(value = "/sales-order", method = RequestMethod.GET)
	ModelAndView generatePdfSalesOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"sales-order.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<SalesOrder> salesOrders = salesOrderService.selectAll();

	return new ModelAndView("pdfViewSalesOrder","salesOrders",salesOrders);
	}
	
	@RequestMapping(value = "/adjustment", method = RequestMethod.GET)
	ModelAndView generatePdfAdjustment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"adjustment.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<Adjustment> adjustments = adjustmentService.selectAll();

	return new ModelAndView("pdfViewAdjustment","adjustments",adjustments);
	}
	
	
}
