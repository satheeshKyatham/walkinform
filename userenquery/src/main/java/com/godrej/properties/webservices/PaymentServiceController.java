package com.godrej.properties.webservices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.PaymentPlanDue;
import com.godrej.properties.model.PaymentPlanLineItem;
import com.godrej.properties.service.EOIPaymentDtlService;
import com.godrej.properties.service.PaymentPlanDueService;
import com.godrej.properties.service.PaymentPlanLineItemService;
import com.godrej.properties.service.PrePaymentReceivedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class PaymentServiceController {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EOIPaymentDtlService eoiPaymentService;
	
	@Autowired
	private PrePaymentReceivedService prePaymentService;
	
	@Autowired
	private PaymentPlanDueService paymentPlanDueService;
	
	@Autowired
	private	PaymentPlanLineItemService paymentPlanLineItemService;
	
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
	@GetMapping(value = "/getPrePayemntData", produces = "application/json")
	public String getPrePayemntData(@RequestParam("offersfid") String offersfid)
	{
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(prePaymentService.getPrePaymentDetails(offersfid));	
	}
	/*	Added By Satheesh Kyatham - 23-04-2020
	 *  Task : Get Payment plan Due Data for CIP App*/
	@GetMapping(value = "/getpaymentplanlist_due", produces = "application/json")
	public String getpaymentplanlist(@RequestParam("project_sfid") String project_sfid,@RequestParam("tower_sfid") String tower_sfid,@RequestParam("payment_plan_sfid") String payment_plan_sfid) { 
		Gson gson = new GsonBuilder().serializeNulls().create();
		//call payment table
		List<PaymentPlanLineItem> paymentPlanLineItems=null;
		String paymentPlanLineItems_str = paymentPlanDueService.getPaymentDueList(project_sfid,tower_sfid,payment_plan_sfid);
		if(paymentPlanLineItems_str==null)
		{
		  paymentPlanLineItems= paymentPlanLineItemService.getpaymentplanlist(payment_plan_sfid);
		  return "{\"id\":0,\"bookingamount\":0,\"dues_amount\":0,\"days\":0,\"msg\":\"\",\"paymentPlanList\":"+gson.toJson(paymentPlanLineItems)+"}";
		}
		else
		{
			return paymentPlanLineItems_str;
		}
	}
}
