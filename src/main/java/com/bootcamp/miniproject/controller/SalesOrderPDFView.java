package com.bootcamp.miniproject.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.SalesOrder;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class SalesOrderPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<SalesOrder> salesOrders = (List<SalesOrder>) model.get("salesOrders");
		   
		   PdfPTable table = new PdfPTable(3);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell("Created Date");
			table.addCell("Customer ");
			table.addCell("Grand Total");

			for (SalesOrder so : salesOrders) {
				table.addCell(String.valueOf(so.getCreatedOn()));
				table.addCell((so.getCustomer().getName())); 
				table.addCell(String.valueOf(so.getGrandTotal())); 
			}
			doc.add(table);
	}
}
