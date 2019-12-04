package com.godrej.properties.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.godrej.kyc.connection.DbConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class GeneratePDF {

public static void main(String[] args) {

		try {
			PDFReport(20, "20");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
}
	
	
	public static void PDFReport(long timeId, String csData) throws JRException, IOException
	  {	
		Connection c = null;
		try {
			System.out.println("continue");
			DbConnection con = new DbConnection();
			c = con.connect();
			
			System.out.println("continue");
			
			  JRPdfExporter exporter = new JRPdfExporter();
			  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			  /*List<String> list = new ArrayList<String>();
		       
		        list.add("rabi");
		        list.add("anil");*/
		        Map params=new HashMap();
		        
		        
		       // JRDesignStyle 
		        
		        	//long timeId = Long.parseLong("1552741186152");
		       
		       // String data = "<ul><li>New Name: Atul B</li></ul>";
		        
		        
		        //c By A
		           //params.put("timeIt", timeId);
		           params.put("data", csData);
		           
		           
		        //String reportLocation = "F:\\atul-data\\apache-tomcat-9.0.0.M22\\reports\\abc.jrxml";
			//String reportLocation = "C:\\JaspersoftWorkspace\\d4uCostsheet\\costsheet.jrxml";
		           String reportLocation = "C:\\JaspersoftWorkspace\\ProjectCostSheet\\Simple_Blue_Table_Based.jrxml";
			
			
          JasperReport jasperReport = JasperCompileManager.compileReport(reportLocation);
          
          //c by A
          JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, c);
          
          //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params, c);
          
          exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
          exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
          exporter.exportReport();
			
          FileOutputStream fos = null;
          byte[] bytes=outputStream.toByteArray();
       //   byte[] bytes=exporter;
          if(bytes.length>1){
              File someFile = new File("C:\\costSheetPDF\\"+timeId+".pdf");
              fos = new FileOutputStream(someFile);
              fos.write(bytes);
              fos.flush();
              fos.close();
              System.out.println("<<<<<<<<<<<<Report Created>>>>>>>>");
              
              
              
          }
			
          
		} catch (Exception e) {
			throw new RuntimeException("It's not possible to generate the pdf report.", e);
		} finally {
			// it's your responsibility to close the connection, don't forget it!
			if (c != null) {
				try { c.close(); } catch (Exception e) {}
			}
		}
		}
	

}
