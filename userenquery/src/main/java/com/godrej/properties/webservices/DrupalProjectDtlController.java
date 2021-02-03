package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.DrupalProjectDtlService;
import com.godrej.properties.service.EnqAndOfferDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class DrupalProjectDtlController<MultipartFormDataInput> {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private DrupalProjectDtlService drupalProjectDtlService;
	
	//Before booking (on offer creation)
	@RequestMapping(value = "/getDrupalProjectDtl", method = RequestMethod.POST)
	public String getEnqAndOfferDtl(@RequestParam("projectsfid") String projectsfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();
			
		return gson.toJson(drupalProjectDtlService.getProjectDtl(projectsfid));
	}
	
}