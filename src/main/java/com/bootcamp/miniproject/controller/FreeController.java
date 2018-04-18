package com.bootcamp.miniproject.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.miniproject.model.Adjustment;
import com.bootcamp.miniproject.model.Employee;
import com.bootcamp.miniproject.model.EmployeeOutlet;
import com.bootcamp.miniproject.model.Outlet;
import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.bootcamp.miniproject.model.TransferStock;
import com.bootcamp.miniproject.model.User;
import com.bootcamp.miniproject.service.AdjustmentService;
import com.bootcamp.miniproject.service.EmployeeService;
import com.bootcamp.miniproject.service.OutletService;
import com.bootcamp.miniproject.service.PurchaseOrderService;
import com.bootcamp.miniproject.service.PurchaseRequestService;
import com.bootcamp.miniproject.service.TransferStockService;

@Controller
@RequestMapping("/free-autentication")
public class FreeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	OutletService outletService;
	
	@Autowired
	TransferStockService transferStockService;
	
	@Autowired
	AdjustmentService adjustmentService;
	
	@Autowired
	PurchaseRequestService purchaseRequestService;

	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	HttpSession httpSession;
	
	@RequestMapping(value="/get-one-by-username")
	@ResponseBody
	public List<Employee> getOneByUsername(@RequestParam(value="username", defaultValue="") String username) {
		return employeeService.getOneByUsername(username);
	}
	
	@RequestMapping(value="/choose-outlet")
	public String chooseOutlet(Model model, Principal principal) {
		String username = principal.getName();
		List<Employee> employee = employeeService.getOneByUsername(username);
		
		Employee empl = new Employee();
		for(Employee emp : employee) {
			empl = emp;
		}
		User user = employeeService.getUserByEmployee(empl);
		httpSession.setAttribute("username", username);
		httpSession.setAttribute("employee", empl);
		httpSession.setAttribute("userLogin", user);
		List<EmployeeOutlet> empOutlets = empl.getEmployeeOutlets();
		List<Outlet> outlets = new ArrayList<>();
		for(EmployeeOutlet eo : empOutlets) {
			Outlet out = eo.getOutlet();
			outlets.add(out);
		}
		System.out.println(empOutlets.size()+" "+outlets.size());
		model.addAttribute("outlets", outlets);
		return "choose-outlet";
	}
	//
	@RequestMapping(value="/home")
	public String home(Model model,@RequestParam(value="id", defaultValue="") long id) {
		Outlet outlet =  outletService.getOne(id);
		httpSession.setAttribute("outlet", outlet);
		Employee emp = (Employee) httpSession.getAttribute("employee");
		if (emp.getUser().getRole().getName().equals("ROLE_CASHIER")) {
			return "sales-order";
		} else {
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
	//
}
