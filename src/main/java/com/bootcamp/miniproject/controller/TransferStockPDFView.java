package com.bootcamp.miniproject.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.TransferStock;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TransferStockPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<TransferStock> tss = (List<TransferStock>) model.get("tss");
		   
		   PdfPTable table = new PdfPTable(5);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell("Created Date");
			table.addCell("From Outlet");
			table.addCell("To Outlet");
			table.addCell("Notes");
			table.addCell("Status");

			for (TransferStock tsss : tss) {
				table.addCell(String.valueOf(tsss.getCreatedOn()));
				table.addCell(tsss.getFromOutlet().getName());
				table.addCell(tsss.getToOutlet().getName());
				table.addCell(tsss.getNotes());
				table.addCell(tsss.getStatus());
			}
			HTMLWorker htmlWorker = new HTMLWorker(doc);
		      String str = "<html><head></head><body>"+
		        "<h1 style='text-align: center;'>Transfer Stock List</h1>" +
		        "<br/>" +
		        "</body></html>";
		      htmlWorker.parse(new StringReader(str));
			doc.add(table);
	}
}
