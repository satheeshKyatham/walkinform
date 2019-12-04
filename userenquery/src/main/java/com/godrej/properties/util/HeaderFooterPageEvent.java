package com.godrej.properties.util;


import java.io.FileOutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	public void onStartPage(PdfWriter writer, Document document) {
	     try {
	        addHeader(document); //adding image
	        ColumnText.showTextAligned(writer.getDirectContent(), 
	        Element.ALIGN_CENTER, new Phrase(), 30, 800, 0);
	        ColumnText.showTextAligned(writer.getDirectContent(), 
	        Element.ALIGN_CENTER, new Phrase(), 550, 800, 0);
	      } catch (DocumentException e) {
	        e.printStackTrace();
	     }
	}
	
	public void onEndPage(PdfWriter writer, Document document) {
		addFooter(writer); //adding company address
		ColumnText.showTextAligned(writer.getDirectContent(), 
		Element.ALIGN_CENTER, new Phrase("page " + document.getPageNumber()
		), 550, 30, 0); 
	}
	
	private void addFooter(PdfWriter writer){
	    PdfPTable footer = new PdfPTable(1);
	    try {
	        footer.setTotalWidth(650);
	        
	        Font smallfont     = new Font(Font.FontFamily.HELVETICA,  8, Font.NORMAL); 
	        
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("First/Sole Applicant: _____________________, Second Applicant: _____________________, Third Applicant: _____________________", smallfont));
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	       
	        
	        footer.addCell(cell);
	        com.itextpdf.text.pdf.PdfContentByte canvas = writer.getDirectContent();
	        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
	        footer.writeSelectedRows(0, -1, 34, 50, canvas);
	        canvas.endMarkedContentSequence();
	    	
	    	// PdfWriter pdfWriter = PdfWriter.getInstance
	  	          

			/*
			 * XMLWorkerHelper worker = XMLWorkerHelper.getInstance(); String str =
			 * "<html><head><style></style></head><body> Test 123 123 13456465f gffdg d65f46</body></html>"
			 * ; worker.parseXHtml(writer, null, new StringReader(str));
			 */
	    	
	    	
	    } catch(Exception de) {
	        throw new ExceptionConverter(de);
	    }
	  }
	 
	  private void addHeader(Document document) throws DocumentException {
		/*
		 * Base64ImageProvider provider = new Base64ImageProvider(); Image image =
		 * provider.retrieve(IMG_DATA); image.scaleAbsolute(PageSize.A4.getWidth(),100);
		 * document.add(image);
		 */
	  }
	
}










/*
 * public class GeneratePDF {
 * 
 * public void createPDF(){ try { FileOutputStream fos = new
 * FileOutputStream("D:/AddImage1111.pdf"); Document document = new
 * Document(PageSize.A4, 15, 15, 20, 80); PdfWriter writer =
 * PdfWriter.getInstance(document, fos); PageChangeEventHandler eventHandler =
 * new PageChangeEventHandler(); writer.setPageEvent(eventHandler);
 * document.open ();
 * 
 * Font headerFont = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD);
 * Paragraph paragraph = new Paragraph("Verification", headerFont);
 * document.add(paragraph); document.add(Chunk.SPACETABBING); PdfPTable table =
 * new PdfPTable(1); table.setWidthPercentage(100); StringBuilder sb_str = new
 * StringBuilder(); sb_str.append("Hitting continued support from Indianwe\n");
 * final String content = sb_str.toString(); cell = new PdfPCell(new
 * Paragraph(content)); table.addCell(cell); PdfPCell emptyCell = new
 * PdfPCell(new Paragraph("")); table.addCell(emptyCell); document.add(table);
 * document.close (); } catch (FileNotFoundException e | Exception e ) {
 * e.printStackTrace(); }
 * System.out.println("******** PDF Created ***************"); } }
 */
 
/*
 * public class PageChangeEventHandler extends PdfPageEventHelper { public void
 * onStartPage(PdfWriter writer, Document document) { try { addHeader(document);
 * //adding image ColumnText.showTextAligned(writer.getDirectContent(),
 * Element.ALIGN_CENTER, new Phrase(), 30, 800, 0);
 * ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
 * new Phrase(), 550, 800, 0); } catch (DocumentException e) {
 * e.printStackTrace(); } }
 * 
 * public void onEndPage(PdfWriter writer, Document document) {
 * addFooter(writer); //adding company address
 * ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
 * new Phrase("page " + document.getPageNumber(), footerFont), 550, 30, 0); }
 * 
 * private void addFooter(PdfWriter writer){ PdfPTable footer = new
 * PdfPTable(1); try { footer.setTotalWidth(527); PdfPCell cell; cell = new
 * PdfPCell(new Phrase("CompanyName/nAddress/nPhone No./n website:",
 * footerFont)); cell.setBorder(Rectangle.NO_BORDER);
 * cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); footer.addCell(cell); //
 * write page PdfContentByte canvas = writer.getDirectContent();
 * canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
 * footer.writeSelectedRows(0, -1, 34, 50, canvas);
 * canvas.endMarkedContentSequence(); } catch(Exception de) { throw new
 * ExceptionConverter(de); } }
 * 
 * private void addHeader(Document document) throws DocumentException {
 * Base64ImageProvider provider = new Base64ImageProvider(); Image image =
 * provider.retrieve(IMG_DATA); image.scaleAbsolute(PageSize.A4.getWidth(),100);
 * document.add(image); } }
 */