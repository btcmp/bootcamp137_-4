package com.bootcamp.miniproject.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.ItemInventory;
import com.bootcamp.miniproject.model.PurchaseRequest;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PurchaseRequestPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<PurchaseRequest> prList = (List<PurchaseRequest>) model.get("purchaseRequest");
		//System.out.println(inv.size());
		  PdfPTable table = new PdfPTable(5);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell("Outlet Name");
			table.addCell("PR No");
			table.addCell("Ready Time");
			table.addCell("Notes");
			table.addCell("Status");

			for (PurchaseRequest pr:prList) {
				
				table.addCell(pr.getOutlet().getName());
				table.addCell(pr.getPrNo());
				table.addCell(String.valueOf(pr.getReadyTime()));
				table.addCell(pr.getPrNo());
				table.addCell(pr.getStatus());
			}
			doc.add(table);
		
	}
	

}
