package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.PaymentEOIReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class PaymentEOIReportController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private PaymentEOIReportService paymentEOIReportService;
	
	
	@RequestMapping(value = "/getPaymentEOIReport", method = RequestMethod.GET, produces = "application/json")
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";
		
		if((fromDate!=null && fromDate.length()>0) && (toDate!=null && toDate.length()>0)) {
			whereCondition = " project_sfid= '"+projectSfid+"' and Date(date_of_eoi__c) between '"+fromDate+"' and '"+toDate+"' order by date_of_eoi__c desc, enq_name  ";
		} else if(projectSfid!=null && projectSfid.length()>0) {
			whereCondition = " project_sfid= '"+projectSfid+"' order by date_of_eoi__c desc, enq_name  ";
		}
		
		return gson.toJson(paymentEOIReportService.getPaymentEOIReportDtl(whereCondition));
	}
	
	
}