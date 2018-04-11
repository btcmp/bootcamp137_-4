package com.bootcamp.miniproject.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.SalesOrderDetail;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
@Controller
public class SalesOrderPDFView extends AbstractPdfView {
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<SalesOrderDetail> salesOrderDetails = (List<SalesOrderDetail>) model.get("salesOrderDetails");
		int b = salesOrderDetails.size();
		int a = 1;
			for (SalesOrderDetail sod : salesOrderDetails) {
		    	  PdfPTable table = new PdfPTable(4);
		    	  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				if (a==1) {
					HTMLWorker htmlWorker = new HTMLWorker(doc);
				      String str = "<html><head></head><body>"+
						"<br/>" +
				        "<h1 style='text-align: center;'>Receipt</h1>" +
				        "<br/>" +
				        "<p>Date : "+String.valueOf(sod.getSalesOrder().getCreatedOn())+ "</p>" +
				        "<p>Customer : "+sod.getSalesOrder().getCustomer().getName()+ "</p>" +
				        "<br/>" +
				        "</body></html>";
				      htmlWorker.parse(new StringReader(str));
						table.addCell("Item");
						table.addCell("Price ");
						table.addCell("Qty.");
						table.addCell("Subtotal");
				}
						table.addCell(sod.getItemVariant().getItem().getName()+"-"+sod.getItemVariant().getName());
						table.addCell(String.valueOf(sod.getUnitPrice())); 
						table.addCell(String.valueOf(sod.getQty()));
						table.addCell(String.valueOf(sod.getSubTotal()));
				if (a==b) {
					table.addCell("Total");
				      table.addCell("");
				      table.addCell("");
				      table.addCell(String.valueOf(sod.getSalesOrder().getGrandTotal()));
				}
			      
				
				doc.add(table);
				a++;
			}
			
	}
}
