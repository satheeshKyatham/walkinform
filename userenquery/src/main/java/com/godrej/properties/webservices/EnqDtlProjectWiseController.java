package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.EnqDtlProjectWiseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class EnqDtlProjectWiseController<MultipartFormDataInput> {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private EnqDtlProjectWiseService enqDtlProjectWiseService;
	
	@RequestMapping(value = "/getEnqForAdminInventoryHold", method = RequestMethod.POST)
	public String getEnqDtl(@RequestParam("enqName") String enqName, @RequestParam("projectSFID") String projectSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();	
			
		return gson.toJson(enqDtlProjectWiseService.getEnqForAdminUnitHold(enqName, projectSFID));
	}
}