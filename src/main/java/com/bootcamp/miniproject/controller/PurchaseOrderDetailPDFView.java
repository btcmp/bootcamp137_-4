package com.bootcamp.miniproject.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.PurchaseOrderDetail;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PurchaseOrderDetailPDFView extends AbstractPdfView{
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<PurchaseOrderDetail> poDetail = (List<PurchaseOrderDetail>) model.get("purchaseOrderDetails");
		int a = 1;
		for (PurchaseOrderDetail pod : poDetail) {
			PdfPTable table = new PdfPTable(4);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		    if (a==1) {
				   HTMLWorker htmlWorker = new HTMLWorker(doc);
				   String str = "<html><head></head><body>"+
						"<br/>" +
				        "<h1 style='text-align: center;'>Purchase Order Detail</h1>" +
				        "<br/>" +
				        "<p>Date : "+String.valueOf(pod.getPurchaseOrder().getPurchaseRequest().getReadyTime())+ "</p>" +
				        "<p>Supplier : "+pod.getPurchaseOrder().getSupplier().getName()+ "</p>" +
				        "<p>From : "+pod.getPurchaseOrder().getOutlet().getName()+ "</p>" +
				        
				        "<p>Status : "+pod.getPurchaseOrder().getStatus()+ "</p>" +
				        "<p>Notes : "+pod.getPurchaseOrder().getNotes()+ "</p>" +
				        "<br/>" +
				        "</body></html>";
				   htmlWorker.parse(new StringReader(str));
				   table.addCell("Item");
				   table.addCell("Request Qty. ");
				   table.addCell("Unit Cost ");
				   table.addCell("Sub Total ");
				   
			}
		    table.addCell(pod.getVariant().getItem().getName() +"-"+pod.getVariant().getName());
			table.addCell(String.valueOf(pod.getRequestQty()));
			table.addCell(String.valueOf(pod.getUnitCost()));
			table.addCell(String.valueOf(pod.getSubTotal()));
			doc.add(table);
			
			a++;
		    
		}
	}
}
