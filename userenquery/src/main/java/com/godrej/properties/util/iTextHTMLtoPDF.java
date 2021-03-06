package com.godrej.properties.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*import org.apache.log4j.Logger;*/

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import net.sf.jasperreports.engine.JRException;

public class iTextHTMLtoPDF {
	/*static Logger logger = Logger.getLogger(iTextHTMLtoPDF.class);*/
	private static Logger logger = LogManager.getLogger(iTextHTMLtoPDF.class);
	public static void main( String[] args ) throws DocumentException, IOException
    {
		try {
			PDFReport("USEREMAIL_GV", "unitTval", "floorTval", "towerName", "regionName", "projectSfid", "unitSfid", 20, "20", "ProjectName", "Date", "enqSfid", null, null);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			logger.error("Error : ",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error : ",e);
		}	
    }
	
	public static void PDFReport(String USEREMAIL_GV, String unitTval, String floorTval, String towerName, String regionName, String projectSfid, String unitSfid, long timeId, String csData, String projectName, String currentDate, String enqSfid, String annexureName, String csLogo) throws JRException, IOException {
		
		try {
			
			String rootPath = System.getProperty("catalina.home");	
			
			String floorName = "";
			
	      Document document = new Document(PageSize.A4);
	      
	      document.setMargins(20, 20, 0, 10);
	     
	      
	      if (floorTval.equals("")) {
	    	  floorName = "NoFloor";
	      } else {
	    	  floorName = floorTval;
	      }
	      
	      
			//Create Folder	      
			File ad_dir = new File(rootPath + File.separator + "costSheetPDF" + File.separator + regionName + File.separator + projectName + File.separator + towerName + File.separator + floorName + File.separator + unitTval);
			String ad_path =ad_dir +File.separator;
			if (!ad_dir.exists()) {
				ad_dir.mkdirs();	
			}
			/*
			 * byte[] abytes; abytes = panAttach.getBytes(); File aserverFile = new
			 * File(ad_path); BufferedOutputStream stream = new BufferedOutputStream(new
			 * FileOutputStream(aserverFile)); stream.write(abytes); stream.close();
			 */
			//END Create Folder
	      
	      
	      PdfWriter pdfWriter = PdfWriter.getInstance
	           (document, new FileOutputStream(ad_path+ enqSfid+"-"+unitSfid+"-"+projectSfid+".pdf"));
	      document.open();
			/*
			 * document.addAuthor("Real Gagnon"); document.addCreator("Real's HowTo");
			 * document.addSubject("Thanks for your support"); document.addCreationDate();
			 * document.addTitle("Please read this");
			 */

	      XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

	      /*
	      String annexureName = "";
	      if (projectSfid.equals("a1l2s000000XoezAAC") || projectSfid.equals("a1l6F000003TRcCQAW") || projectSfid.equals("a1l6F000005TUCvQAO") || projectSfid.equals("a1l2s00000000pEAAQ") || projectSfid.equals("a1l6F000004LVk8QAG")  || projectSfid.equals("a1l2s00000003lPAAQ")  || projectSfid.equals("a1l6F000005hPm5QAE") ) {
              annexureName = "Annexure E";
           } else if (projectSfid.equals("a1l6F000002X6IOQA0")) {
                  annexureName = "Annexure D";
           } else {
              annexureName = "Annexure F";
           }
           */

	      
	      //String csLogo = "";
	      
	      //if (projectSfid.equals("a1l2s00000003VlAAI")) {
	      /*if (projectSfid.equals("a1l2s000000PGu3AAG") || projectSfid.equals("a1l2s000000PGu8AAG")  || projectSfid.equals("a1l2s000000PGuDAAW") || projectSfid.equals("a1l2s000000PGuIAAW")  || projectSfid.equals("a1l2s000000PGuNAAW") || projectSfid.equals("a1l2s000000PGuSAAW")) {
	    	  csLogo = "<img width=\"300\" height=\"40\"  src=\"https://d4u.gplapps.com:8085/walkinform/resources/images/gplLogoRoyalWoods.jpg\"/>"; 
	      } else {
	    	  csLogo = "<img width=\"191\" height=\"50\"  src=\"https://d4u.gplapps.com:8085/walkinform/resources/images/gplLogo.jpg\"/>";
	      }*/
	      
	      
	      
	      if (projectSfid.equals("a1l6F000002X6IOQA0") ) {
	    	  projectName = "The Highlands at Godrej City, Panvel";
	      } 
	      
	      String str = "<html><head><style> .tncData, .tncData td, .tncData th, .tncData li, .tncData b, .tncData span, .tncData span, .tncData div, .tncData label, .tncData strong {font-size:8px;} h4, h5 {font-size:10px !important; font-weight: normal !important;} txtRight {text-align:right !important;} h5 {margin:0 !important;} table {margin-bottom:10px !important;} body{} .table-bordered {border: 0; border-color: #dddddd; border-right-width: 1px; border-right-style: solid;  border-bottom-width: 1px; border-bottom-style: solid;} table {border-collapse: collapse; border-spacing: 1px; } 	th {border: 0; border-color: #dddddd; border-left-width: 1px; border-left-style: solid;  border-top-width: 1px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; } td {border: 0; border-color: #dddddd; border-left-width: 1px; border-left-style: solid;  border-top-width: 1px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; }	 #printColLeft {width:350px !important;} .subHead {background-color: #ebebeb;} th, td {padding:3px;}</style></head><body> <div style='float:left; width:100%; font-size:8px !important;'>  <div style='float:left;' > "+csLogo+" <div style='float:none; clear:both;'></div> </div> <div style='float:left; padding-left:20px;'> <div style=\"margin-bottom:10px;\"> Project: <b class=\"printProjectName\">"+projectName+"</b>  </div>  <div style=\"\">  <div>Head Office: <strong>Godrej Properties Limited</strong> </div>   <div>Godrej One, 5th Floor, Pirojshanagar, Eastern Express Highway,</div> 			<div>Vikhroli (E), Mumbai - 400079, Tel: +91-22-6169 8606</div>  </div>  </div> <div style='float:right;'> <div style='height:30px; text-align:right;'> Date: "+currentDate+"</div> <div style='text-align:right;'>"+annexureName+"</div> <div style='text-align:left; float:right;'>"+enqSfid+"</div> <div style='text-align:right; font-size:6px !important'>"+USEREMAIL_GV+"</div>  </div> <div style=\"clear:both; float:none;\"></div> </div> <div style='float:none; clear:both;'></div> <br></br> <hr></hr>"+csData+"</body></html>";
	      worker.parseXHtml(pdfWriter, document, new StringReader(str));
	      document.close();
	      logger.info("Done 123.");
	      
	      
	      
	      
	      }
	    catch (Exception e) {
	    	logger.error("Error :-",e);
	    }
	  
	}
	
	
	
public static void ApplicationFormPDF(String appFormData, String enqSfid, String projectName, String reraRegistrationNo, String projectid, String appFormLogo) throws JRException, IOException {
		
		try {
	      Document document = new Document(PageSize.A4);
	      
	      document.setMargins(20, 20, 20, 80);
	      String rootPath = System.getProperty("catalina.home");
	      PdfWriter pdfWriter = PdfWriter.getInstance
	           (document, new FileOutputStream(rootPath+"\\applicationForm\\"+enqSfid+".pdf"));
	     
	      HeaderFooterPageEvent event = new HeaderFooterPageEvent();
	    pdfWriter.setPageEvent(event);
	      
	      document.open();
			
			
			/*
			 * document.addAuthor("Real Gagnon"); document.addCreator("Real's HowTo");
			 * document.addSubject("Thanks for your support"); document.addCreationDate();
			 * document.addTitle("Please read this");
			 */
			 
	      XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
	      
	      String reraLabel = "";
	      String reraWebsite = "";
	      
	      if (projectid.equals("a1l6F000003TXloQAG") ) {
	    	  reraLabel = "WBHIRA Registration No.";
	    	  projectName = "Godrej Se7en, Kolkata";
	      } else if(projectid.equals("a1l2s000000ZtXPAA0") ){
	    	  reraLabel = "WBHIRA Registration No.";
	    	  projectName = "Orchard at Godrej SE7EN";
	      } else if(projectid.equals("a1l2s000000UQ25AAG") ){
	    	  reraLabel = "WBHIRA Registration No.";
	    	  projectName = "Retreat at Godrej Prakriti";
	      }
	      
	      else if(projectid.equals("a1l6F000002X6IOQA0") ){
	    	  projectName = "The Highlands at Godrej City, Panvel";
	    	  reraLabel = "RERA Registration No.";
	      } else if (projectid.equals("a1l6F000004RvPHQA0") ) {
	    	  projectName = "Vanaangan at Godrej Garden City";
	    	  reraLabel = "RERA Registration No.";
	      } else {
	    	  reraLabel = "RERA Registration No.";
	    	 // reraRegistrationNo = reraRegistrationNo;
	      }
	      
	      if (projectid.equals("a1l2s000000XmaMAAS")) {
	    	  reraWebsite = "<div></div> <span style='font-size:10px !important;'>RERA Website: https://haryanarera.gov.in/</span>";
	      } else if (projectid.equals("a1l2s000000g6eqAAA")) {
	    	  reraWebsite = "<div></div> <span style='font-size:10px !important;'>RERA Website: https://rera.karnataka.gov.in/</span>";
	      }else {
	    	  reraWebsite = "";
	      }
	      
	      /*String appFormLogo = "";
	      if (projectid.equals("a1l2s000000PGu3AAG") || projectid.equals("a1l2s000000PGu8AAG")  || projectid.equals("a1l2s000000PGuDAAW") || projectid.equals("a1l2s000000PGuIAAW")  || projectid.equals("a1l2s000000PGuNAAW") || projectid.equals("a1l2s000000PGuSAAW")) {
	    	  appFormLogo = "<img width=\"300\" height=\"40\"  src=\"https://d4u.gplapps.com:8085/walkinform/resources/images/gplLogoRoyalWoods.jpg\"/>"; 
	      } else {
	    	  appFormLogo = "<img height='50' width='191' src='https://d4u.gplapps.com:8085/walkinform/resources/images/gplLogo.jpg' />";
	      }*/
	      //<img height='50' width='191' src='https://d4u.godrejproperties.com/walkinform/resources/images/gplLogo.jpg' />
	      
	      
	      String str = "<html><head><style>body{font-size:12px;}.table-bordered {border: 0; border-color: #333333; border-right-width: 1px; border-right-style: solid;  border-bottom-width: 1px; border-bottom-style: solid;} table {font-size:12px; border-collapse: collapse; border-spacing: 1px; width:100%;} th,td {padding:8px;} th {border: 0; border-color: #333333; border-left-width: 1px; border-left-style: solid;  border-top-width: 1px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; } td {border: 0; border-color: #333333; border-left-width: 1px; border-left-style: solid;  border-top-width: 1px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; }</style></head><body><table class='' style='margin-top:0px; margin-bottom:15px; text-align:center; font-size:16px;'> <tbody> <tr> <th style='text-transform: uppercase !important; height:100px;  color:#000000;   vertical-align: top; font-size:20px !important; text-align:left; border: 0; border-color: #ffffff; border-left-width: 0px; border-left-style: solid;  border-top-width: 0px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; '> "+appFormLogo+" </th> <th id='appProjectName' style='text-transform: uppercase !important; height:100px;  color:#666666;   vertical-align: top; font-size:18px !important; text-align:right; border: 0; border-color: #ffffff; border-left-width: 0px; border-left-style: solid;  border-top-width: 0px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; '> "+projectName+" <div></div> <span style='font-size:10px !important;'>"+reraLabel+" "+reraRegistrationNo+"</span> "+reraWebsite+" </th> </tr> <tr> <th colspan='2' style='border: 0; border-color: #ffffff; border-left-width: 0px; border-left-style: solid;  border-top-width: 0px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; '> APPLICATION FORM</th> </tr> </tbody> </table>"+appFormData+"</body></html>";
	      worker.parseXHtml(pdfWriter, document, new StringReader(str));
	      document.close();
	      }
	    catch (Exception e) {
	    	logger.error("Error :-",e);
	    }
	  
	}
	
	
	/* EOI Form Print */
	public static void EOIFormPDF(String appFormData, String regionName, String projectName, String enqName) throws JRException, IOException {
		try {
			Document document = new Document(PageSize.A4);
	      
	      	document.setMargins(20, 20, 20, 80);
	      	
	      	String rootPath = System.getProperty("catalina.home");
	      	//Create Folder	      
			File ad_dir = new File(rootPath + File.separator + "costSheetPDF" + File.separator + regionName + File.separator + projectName + File.separator + "EOI Details" + File.separator + enqName);
			String ad_path =ad_dir +File.separator;
			if (!ad_dir.exists()) {
				ad_dir.mkdirs();	
			}
	      	
	      	PdfWriter pdfWriter = PdfWriter.getInstance
	           (document, new FileOutputStream(ad_path+"eoi_form_"+enqName+".pdf"));
	     
	      	HeaderFooterPageEvent event = new HeaderFooterPageEvent();
	      	pdfWriter.setPageEvent(event);
	      	
	      	document.open();
			 
	      	XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
	      
	      	String str = "<html><head><style>body{font-size:12px;}.table-bordered {border: 0; border-color: #333333; border-right-width: 1px; border-right-style: solid;  border-bottom-width: 1px; border-bottom-style: solid;} table {font-size:12px; border-collapse: collapse; border-spacing: 1px; width:100%;} th,td {padding:8px;} th {border: 0; border-color: #333333; border-left-width: 1px; border-left-style: solid;  border-top-width: 1px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; } td {border: 0; border-color: #333333; border-left-width: 1px; border-left-style: solid;  border-top-width: 1px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; }</style></head><body>"+appFormData+"</body></html>";
	      	worker.parseXHtml(pdfWriter, document, new StringReader(str));
	      	document.close();
	    }
	    catch (Exception e) {
	    	logger.error("Error :-",e);
	    }
	}
	/* END EOI Form Print */


	
}
