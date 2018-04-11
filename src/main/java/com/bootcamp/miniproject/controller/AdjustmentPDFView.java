package com.bootcamp.miniproject.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.Adjustment;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class AdjustmentPDFView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<Adjustment> adjustments = (List<Adjustment>) model.get("adjustments");
		   
		   PdfPTable table = new PdfPTable(3);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell("Adjustment Date");
			table.addCell("Notes");
			table.addCell("Status");

			for (Adjustment adjust : adjustments) {
				table.addCell(String.valueOf(adjust.getCreatedOn()));
				table.addCell(adjust.getNotes()); 
				table.addCell(adjust.getStatus()); 
			}
			HTMLWorker htmlWorker = new HTMLWorker(doc);
		      String str = "<html><head></head><body>"+
		        "<h1 style='text-align: center;'>Adjusment</h1>" +
		        "<br/>" +
		        "</body></html>";
		      htmlWorker.parse(new StringReader(str));

			doc.add(table);
	}
}
