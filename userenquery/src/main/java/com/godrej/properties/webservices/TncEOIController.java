package com.godrej.properties.webservices;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.TnCEOI;
import com.godrej.properties.service.TnCEOIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class TncEOIController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private TnCEOIService tnCEOIService;
	
	/* Insert TNC against Project for EOI form */
	@RequestMapping(value = "/insertTnCForEOI", method = RequestMethod.POST)
	public TnCEOI insertTnCForEOI(@RequestParam("tnc_text") String tnc_text,  @RequestParam("project_id") String project_id, @RequestParam("project_name") String project_name,    @RequestParam("region_id") String region_id, @RequestParam("region_name") String region_name)   
	{	
		TnCEOI oc = new TnCEOI();
		
		oc.setTnc_text(tnc_text);
		oc.setIsactive("A");
		oc.setProject_id(project_id);
		oc.setProject_name(project_name);
		
		oc.setRegion_id(region_id);
		oc.setRegion_name(region_name);
		oc.setCreatedby("9999");
		oc.setUpdatedby("9999");
		
		tnCEOIService.insertTNCForEOI(oc);
		
		oc.setInsertStatus("Successfully Submitted");	
		
		return  (oc);
	}
	/* END Insert TNC against Project for EOI form */
	
	
	/* Get TNC Data */
	@RequestMapping(value = "/getTncEOIData", method = RequestMethod.POST)
	public String insertTnCForPP(@RequestParam("projectid") String projectid) 
	{	
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		List<TnCEOI> tnCEOI = tnCEOIService.getTncData (projectid);
		
		return gson.toJson(tnCEOI);
	}
	/* Get TNC Data */
	
}