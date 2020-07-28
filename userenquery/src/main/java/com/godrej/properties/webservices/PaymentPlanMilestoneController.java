package com.godrej.properties.webservices;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.PaymentPlanMilestone;
import com.godrej.properties.service.PaymentPlanMilestoneService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class PaymentPlanMilestoneController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private PaymentPlanMilestoneService paymentPlanMilestoneService;
	
	@RequestMapping(value = "/getPaymentPlanMilestone", method = RequestMethod.POST)
	public String getFirstMilstone(@RequestParam("paymentPlanSfid") String paymentPlanSfid) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		List<PaymentPlanMilestone> ppMilestoneList =paymentPlanMilestoneService.getPaymentPlanLineItems(paymentPlanSfid);
		return gson.toJson(ppMilestoneList);
	}  
	
}