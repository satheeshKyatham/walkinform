package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.DashboardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class DashboardController<MultipartFormDataInput> {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping(value = "/closingDashboard", method = RequestMethod.POST)
	public String getEnqDtl(@RequestParam("projectSFID") String projectSFID, @RequestParam("userid") String userid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();	
		
		return gson.toJson(dashboardService.getDashboard(projectSFID, userid));
	}
}