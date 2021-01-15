package com.godrej.properties.webservices;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/file")
public class ViewFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName=request.getParameter("name");
		
		String from=request.getParameter("from");
		
		String eid=request.getParameter("eid");
		
		String fid=request.getParameter("fid");
		
		
		String rootPath = System.getProperty("catalina.home");
		
		File file=null;
		
		if (from.equals("EOIbookingReference")) {
			file = new File(rootPath+"\\"+from+"\\"+eid+"\\"+fid+"\\"+fileName);
		} else if (from.equals("eoiForm")) {
			file = new File(rootPath+"\\"+fileName);
			fileName = file.getName();
		} else if(from.equals("D4U File Storage")){
			String regionname=request.getParameter("rname");
			String projecctName=request.getParameter("pname");
			file = new File(rootPath+"\\"+from+"\\"+regionname+"\\"+projecctName+"\\EOIREFUNDCHEQUE\\"+eid+"\\"+fileName);
		}
		else if(from.equals("ExternalAccessFiles")){
			String regionname=request.getParameter("regionname");
			file = new File(rootPath+"\\"+from+"\\"+regionname+"\\"+fileName);
		}
	    response.setHeader("Content-Type",    getServletContext().getMimeType(file.getName()));
	    response.setHeader("Content-Length", String.valueOf(file.length()));
	    response.setHeader("Content-Disposition", "inline; filename="+fileName);
	    Files.copy(file.toPath(), response.getOutputStream());
	    
	}
	
}