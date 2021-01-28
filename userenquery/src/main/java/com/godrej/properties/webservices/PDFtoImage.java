package com.godrej.properties.webservices;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFtoImage {

	public static void main(String[] args) throws IOException {
		
		/*String rootPath = System.getProperty("catalina.home");	
		
		
		String in = "C:\\Users\\gc.atulbhanushali\\testxyz.pdf";
		
		String out =  "C:\\Users\\gc.atulbhanushali";
		
		PDDocument pd = PDDocument.load (new File (in));
	    PDFRenderer pr = new PDFRenderer (pd);
	    BufferedImage bi = pr.renderImageWithDPI (0, 300);
	    ImageIO.write (bi, "JPEG", new File (out)); */
		
		//-------------------------
		
		//pdfToImage("D:\\atul_data\\apache-tomcat-9.0.22\\costSheetPDF\\Mumbai\\Godrej City, Panvel, Mumbai\\The Highlands Tower 1\\4th floor\\403\\Draft-a1u2s000000VyyCAAS-a1s2s000000PsgdAAC-a1l6F000002X6IOQA0.pdf");
		
	}
	
	public static void pdfToImage(File file, String folderPath) {
	//private static void pdfToImage(String pdfPath) {
		
		String pdfPath = file.toPath().toString();
				
		try (PDDocument doc = PDDocument.load(new File(pdfPath))) {
			PDFRenderer pdfRenderer = new PDFRenderer(doc); 
			
			for (int page = 0;page<doc.getNumberOfPages();++page) {
				BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 150, ImageType.RGB);
				//String fileName = "D:\\atul_data\\apache-tomcat-9.0.22\\costSheetPDF\\Mumbai\\Godrej City, Panvel, Mumbai\\The Highlands Tower 1\\4th floor\\403\\image-"+page+".png";
				
				String imageName = file.getName();
				imageName = imageName.substring(0, imageName.lastIndexOf(".")+1);
				
				String fileName = folderPath+imageName+"png";
				
				ImageIOUtil.writeImage(bim, fileName, 150);
			}
			doc.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
