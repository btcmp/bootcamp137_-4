package com.bootcamp.miniproject.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.PurchaseRequestDetail;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PurchaseRequestDetailPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<PurchaseRequestDetail> prDetail = (List<PurchaseRequestDetail>) model.get("purchaseRequestDetails");
		int a = 1;
		for (PurchaseRequestDetail prd : prDetail) {
			PdfPTable table = new PdfPTable(2);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		    if (a==1) {
				   HTMLWorker htmlWorker = new HTMLWorker(doc);
				   String str = "<html><head></head><body>"+
						"<br/>" +
				        "<h1 style='text-align: center;'>Transfer Stock Detail</h1>" +
				        "<br/>" +
				        "<p>Date : "+String.valueOf(prd.getPurchaseRequest().getReadyTime())+ "</p>" +
				        "<p>From : "+prd.getPurchaseRequest().getOutlet().getName()+ "</p>" +
				        
				        "<p>Status : "+prd.getPurchaseRequest().getStatus()+ "</p>" +
				        "<p>Notes : "+prd.getPurchaseRequest().getNotes()+ "</p>" +
				        "<br/>" +
				        "</body></html>";
				   htmlWorker.parse(new StringReader(str));
				   table.addCell("Item");
				   table.addCell("Request Qty. ");
				   
			}
		    table.addCell(prd.getItemVariant().getItem().getName() +"-"+prd.getItemVariant().getName());
			table.addCell(String.valueOf(prd.getRequestQty()));
			doc.add(table);
			a++;
		    
		}
	}

}
