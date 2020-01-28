package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.EOIData;
import com.godrej.properties.service.KYCApplicantDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class KYCApplicantDtlServiceController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private KYCApplicantDtlService kYCapplicantDtlService;
	
	//Before booking (on offer creation)
	@PostMapping(value = "/getKYCApplicantData")
	public String getKYCApplicantData(@RequestParam("enqName") String enqName, @RequestParam("contactSFID") String contactSFID) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
			
		return gson.toJson(kYCapplicantDtlService.getApplicantData(enqName, contactSFID));
	}
	
	@GetMapping(value = "/getKYCData")
	public String getKYCData(@RequestParam("userid") String userid, @RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(kYCapplicantDtlService.getKYCDataVW(userid, projectid));
	}
	
	@PostMapping(value = "/getKYCStatus")
	public String getKYCStatus(@RequestParam("enquiryName") String enquiryName, @RequestParam("projectid") String projectid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(kYCapplicantDtlService.getKYCStatus(enquiryName, projectid));
	}
}