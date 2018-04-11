package com.bootcamp.miniproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.miniproject.model.Adjustment;
import com.bootcamp.miniproject.model.Category;
import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.SalesOrder;
import com.bootcamp.miniproject.model.SalesOrderDetail;
import com.bootcamp.miniproject.model.Supplier;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.model.TransferStockDetail;
import com.bootcamp.miniproject.model.User;
import com.bootcamp.miniproject.service.AdjustmentService;
import com.bootcamp.miniproject.service.CategoryService;
import com.bootcamp.miniproject.service.ItemInventoryService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.PurchaseOrderService;
import com.bootcamp.miniproject.service.PurchaseRequestService;
import com.bootcamp.miniproject.service.SalesOrderService;
import com.bootcamp.miniproject.service.SupplierService;
import com.bootcamp.miniproject.service.TransferStockDetailService;
import com.bootcamp.miniproject.service.TransferStockService;

@Controller
@RequestMapping("/generate")
public class ExportPDFController {
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	PurchaseRequestService purchaseRequestService;
	
	@Autowired
	TransferStockService tsService;
	
	@Autowired
	TransferStockDetailService tsdService;
	
	@Autowired
	OutletService outletService; 
	
	@Autowired
	CategoryService categoryService; 
	
	@Autowired
	SalesOrderService salesOrderService; 
	
	@Autowired
	AdjustmentService adjustmentService;
	
	@Autowired
	ItemInventoryService inventoryService;
	
	@Autowired
	PurchaseRequestService prService;
	
	@Autowired
	PurchaseOrderService poService;
	
	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	ModelAndView generatePdf(HttpServletRequest request,
	HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"suppliers.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<Supplier> suppliers = supplierService.selectStatusActive();

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
	ModelAndView generatePdfTransferStock(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"transfer_stock.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<TransferStock> tss = tsService.selectAll();

	return new ModelAndView("pdfViewTS","tss",tss);
 	}
	
	@RequestMapping(value = "/ts-detail/{id}", method = RequestMethod.GET)
	ModelAndView generatePdfTransferStockDetail(HttpServletRequest request, HttpServletResponse response,@PathVariable long id) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"transfer_stock_detail.pdf\"");
		response.setContentType("application/pdf");
		List<TransferStockDetail> transferStockDetails = tsdService.getTransferStockDetailsByTransferStockId(id);

	return new ModelAndView("pdfViewTransferStockDetail","transferStockDetails",transferStockDetails);
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
	
	@RequestMapping(value = "/sales-order/{id}", method = RequestMethod.GET)
	ModelAndView generatePdfSalesOrder(HttpServletRequest request, HttpServletResponse response,@PathVariable long id) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"sales-order.pdf\"");
		response.setContentType("application/pdf");
		List<SalesOrderDetail> salesOrderDetails = salesOrderService.getSODBySOId(id);

	return new ModelAndView("pdfViewSalesOrder","salesOrderDetails",salesOrderDetails);
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
	@RequestMapping(value = "/item", method = RequestMethod.GET)
	ModelAndView generatePdfItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"item.pdf\"");
		response.setContentType("application/pdf");
		java.util.List<ItemInventory> inv = inventoryService.getAll();
		//System.out.println(inv.size());
	return new ModelAndView("pdfViewItem","inventories",inv);
	}
	
	@RequestMapping(value = "/purchase-request", method = RequestMethod.GET)
	ModelAndView generatePdfPurchaseRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"purchase request.pdf\"");
		response.setContentType("application/pdf");
		Outlet outlet = (Outlet) httpSession.getAttribute("outlet");
		List<PurchaseRequest> pr = prService.getAllPrByOutlet(outlet.getId());
		return new ModelAndView("pdfViewPR", "purchaseRequest", pr);
	}
	@RequestMapping(value = "/purchase-order", method = RequestMethod.GET)
	ModelAndView generatePdfPurchaseOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Calling generatePdf()...");
		//user data
		response.setHeader("Content-Disposition", "attachment; filename=\"purchase request.pdf\"");
		response.setContentType("application/pdf");
		Outlet outlet = (Outlet) httpSession.getAttribute("outlet");
		List<PurchaseOrder> po = poService.getAllPoByOutlet(outlet.getId());
		return new ModelAndView("pdfViewPR", "purchaseOrder", po);
	}
}
