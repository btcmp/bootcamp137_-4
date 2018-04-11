package com.bootcamp.miniproject.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.TransferStockDetail;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TransferStockDetailPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<TransferStockDetail> transferStockDetails = (List<TransferStockDetail>) model.get("transferStockDetails");
		   int a = 1;
		   for (TransferStockDetail tsd : transferStockDetails) {
			   PdfPTable table = new PdfPTable(3);
			   table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			   table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			   if (a==1) {
				   HTMLWorker htmlWorker = new HTMLWorker(doc);
				   String str = "<html><head></head><body>"+
						"<br/>" +
				        "<h1 style='text-align: center;'>Transfer Stock Detail</h1>" +
				        "<br/>" +
				        "<p>Date : "+String.valueOf(tsd.getTransferStock().getModifiedOn())+ "</p>" +
				        "<p>From : "+tsd.getTransferStock().getFromOutlet().getName()+ "</p>" +
				        "<p>To : "+tsd.getTransferStock().getToOutlet().getName()+ "</p>" +
				        "<p>Status : "+tsd.getTransferStock().getStatus()+ "</p>" +
				        "<p>Notes : "+tsd.getTransferStock().getNotes()+ "</p>" +
				        "<br/>" +
				        "</body></html>";
				   htmlWorker.parse(new StringReader(str));
				   table.addCell("Item");
				   table.addCell("Transfer Qty. ");
				   table.addCell("In Stock");
			}
			   table.addCell(tsd.getItemVariant().getItem().getName()+"-"+tsd.getItemVariant().getName());
			   table.addCell(String.valueOf(tsd.getTransferQty()));
			   table.addCell(String.valueOf(tsd.getInstock()));
			   doc.add(table);
			   a++;
		   }
		   
	}
}
