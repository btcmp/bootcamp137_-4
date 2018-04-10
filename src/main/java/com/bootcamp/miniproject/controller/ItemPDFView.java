package com.bootcamp.miniproject.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bootcamp.miniproject.model.ItemInventory;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ItemPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<ItemInventory> inv = (List<ItemInventory>) model.get("inventories");
		//System.out.println(inv.size());
		  PdfPTable table = new PdfPTable(4);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			table.addCell("Name");
			table.addCell("Category");
			table.addCell("Unit Price");
			table.addCell("Stock");

			for (ItemInventory iinv : inv) {
				table.addCell(iinv.getItemVariant().getItem().getName() + ' '+iinv.getItemVariant().getName());
				table.addCell(iinv.getItemVariant().getItem().getCategory().getName());
				table.addCell(String.valueOf(iinv.getItemVariant().getPrice()));
				table.addCell(String.valueOf(iinv.getEndingQty()));
			}
			doc.add(table);
		
	}
	
}
