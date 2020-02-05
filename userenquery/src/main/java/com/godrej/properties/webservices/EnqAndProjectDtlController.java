package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.EnqAndProjectDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class EnqAndProjectDtlController<MultipartFormDataInput> {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private EnqAndProjectDtlService enqAndProjectDtlService;
	
	@RequestMapping(value = "/getEnqAndProjectDtl", method = RequestMethod.POST)
	public String getEnqAndProjectDtl(@RequestParam("enqId") String enqId) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
			
		return gson.toJson(enqAndProjectDtlService.getEnqAndProjectData(enqId));
	}
}