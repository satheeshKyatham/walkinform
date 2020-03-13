package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.EOIReportService;
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
	
	@Autowired
 	private EOIReportService eOIReportService;
	
	
	@GetMapping(value = "/getPaymentEOIReport", produces = "application/json")
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
	
	
	@GetMapping(value = "/getPaymentEOIReportSales", produces = "application/json")
	public String getEOIReportDtlSales(@RequestParam("userid") String userid, @RequestParam("projectSfid") String projectSfid, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";
		
		if((fromDate!=null && fromDate.length()>0) && (toDate!=null && toDate.length()>0)  &&  userid!=null && userid.length()>0   ) {
			whereCondition = " project_sfid= '"+projectSfid+"' and Date(date_of_eoi__c) between '"+fromDate+"' and '"+toDate+"' and userid = "+userid+" order by date_of_eoi__c desc, enq_name  ";
		} else if(projectSfid!=null && projectSfid.length()>0 &&  userid!=null && userid.length()>0 ) {
			whereCondition = " project_sfid= '"+projectSfid+"' and userid = "+userid+"  order by date_of_eoi__c desc, enq_name  ";
		}
		
		return gson.toJson(paymentEOIReportService.getPaymentEOIReportDtl(whereCondition));
	}
	
	@GetMapping(value = "/getAllotmentDayReport", produces = "application/json")
	public String getAllotmentDayReport(@RequestParam("projectSfid") String projectSfid, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = " project_sfid= '"+projectSfid+"' and Date(offer_date__c) between '"+fromDate+"' and '"+toDate+"' order by offer_date__c desc  ";
		
		return gson.toJson(eOIReportService.getAllotmentReport(whereCondition));
	}
	
	@GetMapping(value = "/getAllotmentDayMISReport", produces = "application/json")
	public String getAllotmentDayMISReport(@RequestParam("projectSfid") String projectSfid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		//String whereCondition = " propstrength__project__c= '"+projectSfid+"'  ";
		
		return gson.toJson(eOIReportService.getAllotmentMISReport(projectSfid));
	}
	
}