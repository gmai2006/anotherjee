package com.tomcat.hosting.service;

import java.io.OutputStream;

import com.lowagie.text.DocWriter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.SimpleCell;
import com.lowagie.text.Table;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class WriterFactory {

	public WriterFactory() {
	}

	public static DocWriter getWriter(String s, Document document,
			OutputStream out) throws Exception {
		if ("html".equals(s))
			return HtmlWriter.getInstance(document, out);
		else
			return PdfWriter.getInstance(document, out);
	}

	public static Element getTable(String type, int columns) throws Exception {
		if ("html".equals(type))
			return new Table(columns);
		else
			return new PdfPTable(columns);
	}

	public static Rectangle getCell(String type) throws Exception {
		if ("html".equals(type))
			return new SimpleCell(true);
		else
			return new PdfPCell();
	}

	static final String HTML = "html";
	static final String PDF = "pdf";
}
