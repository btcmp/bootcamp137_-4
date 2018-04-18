package com.bootcamp.miniproject.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.PurchaseOrder;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PurchaseOrderPDFView extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<PurchaseOrder> poList = (List<PurchaseOrder>) model.get("purchaseOrder");
		//System.out.println(inv.size());
		HTMLWorker htmlWorker = new HTMLWorker(doc);
		  String str = "<html><head></head><body>"+
				  "<br/>" +
				  "<h2 style='text-align: center;'>Purchase Order List</h2>" +
				  "<br/>" +
				  "<p>Outlet Name : "+poList.get(0).getOutlet().getName()+ "</p>" +
				  "<br/>" +
				  "</body></html>";
		  htmlWorker.parse(new StringReader(str));
		  PdfPTable table = new PdfPTable(5);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell("Outlet Name");
			table.addCell("Supplier Name");
			table.addCell("PO No");
			table.addCell("Notes");
			table.addCell("Status");

			for (PurchaseOrder po:poList) {
				
				table.addCell(po.getOutlet().getName());
				table.addCell(po.getSupplier().getName());
				table.addCell(po.getPoNo());
				table.addCell(po.getNotes());
				table.addCell(po.getStatus());
			}
			doc.add(table);
		
	}
}
