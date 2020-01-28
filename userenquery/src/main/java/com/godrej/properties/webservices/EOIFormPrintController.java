package com.godrej.properties.webservices;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.util.iTextHTMLtoPDF;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.jasperreports.engine.JRException;

@CrossOrigin(origins="*")
@RestController
public class EOIFormPrintController {
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value = { "/printEOIForm" }, method = RequestMethod.POST)
	public synchronized String printApplicationForm (@RequestParam("EOIFormData") String EOIFormData, @RequestParam("regionName") String regionName, @RequestParam("projectName") String projectName, @RequestParam("enqName") String enqName) throws JRException, IOException{
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		iTextHTMLtoPDF solution = new iTextHTMLtoPDF ();
		solution.EOIFormPDF(EOIFormData, regionName, projectName, enqName); 
		
		return gson.toJson("");
	}
}