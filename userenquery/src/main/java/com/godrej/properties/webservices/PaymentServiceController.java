package com.godrej.properties.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.EOIPaymentDtlService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class PaymentServiceController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EOIPaymentDtlService eoiPaymentService;
	
	@GetMapping(value = "/getPendingPaymentData", produces = "application/json")
	public String getPendingPaymentData(@RequestParam("projectid") String projectid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition=" where project_sfid='"+projectid+"' and isactive='N'";
		return gson.toJson(eoiPaymentService.getCommonEOIPaymentEntrys(whereCondition));	
	}
	
	@GetMapping(value = "/getApprovedPaymentData", produces = "application/json")
	public String getApprovedPaymentData(@RequestParam("projectid") String projectid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition=" where project_sfid='"+projectid+"' and isactive='Y'";
		return gson.toJson(eoiPaymentService.getCommonEOIPaymentEntrys(whereCondition));	
	}
	@GetMapping(value = "/getRejectedPaymentData", produces = "application/json")
	public String getRejectedPaymentData(@RequestParam("projectid") String projectid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition=" where project_sfid='"+projectid+"' and isactive='R'";
		return gson.toJson(eoiPaymentService.getCommonEOIPaymentEntrys(whereCondition));	
	}
	@PostMapping(value = "/updateEOIPayment", produces = "application/json")
	public void updateEOIPayment(@RequestParam("paymentEOIDtlJson") String paymentEOIDtlJson)
	{
		eoiPaymentService.paymentEOIApproveReject(paymentEOIDtlJson);	
	}
}
