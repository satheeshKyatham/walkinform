package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.PaymentPlanList;
import com.godrej.properties.service.PaymentPlanListService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class PPListController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private PaymentPlanListService paymentPlanListService;
	
	
	@RequestMapping(value = "/getPPList", method = RequestMethod.POST)
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid) {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String whereCondition = "";
		
		if(projectSfid != null && projectSfid.length()>0) {
			whereCondition = " propstrength__project__c = '"+projectSfid+"' ";
		} else {
			return gson.toJson(null);
		} 
		return gson.toJson(paymentPlanListService.getPPList(whereCondition));
	}
	
	
	
	@RequestMapping(value = "/updatePP", method = RequestMethod.POST)
	public String updatePPData(
			@RequestParam("projectid") String projectid,
			@RequestParam("ppsfid") String ppsfid,
			@RequestParam("action") String action
			) {
			
			 String [] data= ppsfid.split(",");
			 StringBuilder error = new StringBuilder();
			 
			 for (int i=0;i<data.length;i++){
				try {
					PaymentPlanList inventoryAdmin = new PaymentPlanList();
					inventoryAdmin.setSfid(data[i]);
					inventoryAdmin.setPropstrength__project__c(projectid);
					inventoryAdmin.setD4u_active__c(action);
	
					paymentPlanListService.updatePP(inventoryAdmin);
					 
				} catch (Exception e) {
					log.error("error", e);
					error.append("/n Problem in updating the payment plan ");
					//.append(units[i]);
				}
			}
			 
			 
	String errorMessage = error.toString();
	if(errorMessage!=null && !errorMessage.isEmpty()){
		return errorMessage;
	}
	 return "success";
 }
	
}