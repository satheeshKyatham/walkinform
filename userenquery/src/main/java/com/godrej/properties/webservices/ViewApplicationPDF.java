package com.godrej.properties.webservices;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/applicationForm")
public class ViewApplicationPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName=request.getParameter("name");
		
		String rootPath = System.getProperty("catalina.home");
		
		
		
		
			//file = new File(rootPath+"\\"+from+"\\"+eid+"\\"+fid+"\\"+fileName);
		
		
		
		File file=null;
		//if(fileName.contains("ADHAR-"))
		//file = new File("C:\\Files\\"+fileName.split("ADHAR-")[1]+".pdf");
		//else {
			file = new File(rootPath+"\\applicationForm\\"+fileName+".pdf");
		//}
	   // File file = new File("C:\\files\\893329724717.pdf");
	    response.setHeader("Content-Type",    getServletContext().getMimeType(file.getName()));
	    response.setHeader("Content-Length", String.valueOf(file.length()));
	    response.setHeader("Content-Disposition", "inline; filename=\""+fileName+".pdf\"");
	    Files.copy(file.toPath(), response.getOutputStream());
	}
}