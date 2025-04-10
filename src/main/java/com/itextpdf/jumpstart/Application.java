package com.itextpdf.jumpstart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class Application {
	
	public static final String DEST="HelloWorld.pdf";

	public static void main(String[] args) throws IOException{		

		File file=new File(DEST);
		new Application().createPdf(DEST);
	}
	
	public void createPdf(String DEST) throws IOException{
		//Initialize pdfwriter
		FileOutputStream fos=new FileOutputStream(DEST);
		PdfWriter writer=new PdfWriter(fos);
		
		//Initialize pdf document
		PdfDocument pdf=new PdfDocument(writer);
		
		//Initialize document
		Document document=new Document(pdf);
		
		//Add paragraph to the content
		document.add(new Paragraph("Hello World"));
		
		//close document
		document.close();
	}

}
