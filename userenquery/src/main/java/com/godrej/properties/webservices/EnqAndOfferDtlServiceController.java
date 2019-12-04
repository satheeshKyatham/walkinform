package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.EnqAndOfferDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class EnqAndOfferDtlServiceController<MultipartFormDataInput> {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private EnqAndOfferDtlService enqAndOfferDtlService;
	
	//Before booking (on offer creation)
	@RequestMapping(value = "/getEnqAndOfferDtl", method = RequestMethod.POST)
	public String getEnqAndOfferDtl(@RequestParam("enqId") String enqId, @RequestParam("offerSFID") String offerSfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
			
		return gson.toJson(enqAndOfferDtlService.getEnqAndOfferData(enqId, offerSfid));
	}
	
}