package com.bootcamp.miniproject.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.AdjustmentDetail;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class AdjustmentDetailPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<AdjustmentDetail> adjustmentDetails = (List<AdjustmentDetail>) model.get("adjustmentDetails");
		   int a = 1;
		   for (AdjustmentDetail adjustDet : adjustmentDetails) {
			   PdfPTable table = new PdfPTable(3);
			   table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			   table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			   if (a==1) {
				   HTMLWorker htmlWorker = new HTMLWorker(doc);
				   String str = "<html><head></head><body>"+
						"<br/>" +
				        "<h1 style='text-align: center;'>Adjustment Detail</h1>" +
				        "<br/>" +
				        "<p>Created By : "+adjustDet.getAdjustment().getOutlet().getCreatedBy().getUsername()+ "</p>" +
				        "<p>Date : "+String.valueOf(adjustDet.getAdjustment().getModifiedOn())+ "</p>" +
				        "<p>Status : "+adjustDet.getAdjustment().getStatus()+ "</p>" +
				        "<p>Notes : "+adjustDet.getAdjustment().getNotes()+ "</p>" +
				        "<br/>" +
				        "</body></html>";
				   htmlWorker.parse(new StringReader(str));
				   table.addCell("Item");
				   table.addCell("Adjustment Qty. ");
				   table.addCell("In Stock");
			}
			   table.addCell(adjustDet.getItemVariant().getItem().getName()+"-"+adjustDet.getItemVariant().getName());
			   table.addCell(String.valueOf(adjustDet.getInStock()));
			   table.addCell(String.valueOf(adjustDet.getActualStock()));
			   doc.add(table);
			   a++;
		   }
		   
	}
}
