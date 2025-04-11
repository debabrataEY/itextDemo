package com.itextpdf.jumpstart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.jumpstart.util.CsvToPdfConverter;
import com.itextpdf.jumpstart.util.SignPDF;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class Application {
	
	public static final String DEST="HelloWorld1.pdf";	

    public static final String DEST1 = "united_states_generated.pdf";

	public static void main(String[] args) throws Exception{	
		//new Application().createPdf(DEST);
		/*String sourcePdf="HelloWorld.pdf";
		new SignPDF().sign(sourcePdf);*/	
		new CsvToPdfConverter().createPdf(DEST1);
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
