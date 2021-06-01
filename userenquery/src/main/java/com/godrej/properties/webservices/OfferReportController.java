package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.VW_OfferWithBalanceService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class OfferReportController {
	
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private VW_OfferWithBalanceService vW_OfferWithBalanceService;
	
	
	@RequestMapping(value = "/getOfferCancelledList", method = RequestMethod.GET, produces = "application/json")
	public String getOfferList(@RequestParam("projectid") String projectid,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();

		String finalProjectid = "";
		if (projectid != null) {
			// For multiple project report
			String[] multiProjectid = projectid.split(",");
			StringBuilder modifiedProid = new StringBuilder();
			for (int i = 0; i < multiProjectid.length; i++) {
				modifiedProid.append("'" + multiProjectid[i] + "'");
				modifiedProid.append(",");
			}
			finalProjectid = modifiedProid.toString();
			if (finalProjectid != null && finalProjectid.length() > 0
					&& finalProjectid.charAt(finalProjectid.length() - 1) == ',') {
				finalProjectid = finalProjectid.substring(0, finalProjectid.length() - 1);
			}
			// END For multiple project report
		} else {
			finalProjectid = "";
		}
		
		
		// For multiple verticales
		String finalVerticales = "";
		if (userVerticals != null && !userVerticals.equals("null") && !userVerticals.equals("")) {
			String [] multiVerticales= userVerticals.split(",");
			
			StringBuilder modifiedVer = new StringBuilder();
			
			
			for (int i=0;i<multiVerticales.length;i++){
				modifiedVer.append("'"+multiVerticales[i]+"'");
				modifiedVer.append(",");
			}
			
			finalVerticales = modifiedVer.toString();
			
			if (finalVerticales != null && finalVerticales.length() > 0 && finalVerticales.charAt(finalVerticales.length() - 1) == ',') {
				finalVerticales = finalVerticales.substring(0, finalVerticales.length() - 1);
			}
		} else {
			finalVerticales = null;
		}
		// END For multiple verticales
		
		String vertCondition= "";
		
		if (finalVerticales != null) {
			vertCondition = " and b.verticle__c in ("+finalVerticales+")  ";
		} else {
			vertCondition= "";
		}

		String whereCondition = "";

		if ((fromDate != null && fromDate.length() > 0) && (toDate != null && toDate.length() > 0)) {
			whereCondition = " a.isdeleted = 'false' AND a.propstrength__status__c = 'Cancelled' AND a.propstrength__project__c in (" + finalProjectid + ") and Date(a.createddate) between '" + fromDate
					+ "' and '" + toDate + "' " + vertCondition;
		} else if (projectid != null && projectid.length() > 0) {
			whereCondition = " a.isdeleted = 'false' AND a.propstrength__status__c = 'Cancelled' AND a.propstrength__project__c in (" + finalProjectid + ")  " + vertCondition;
		} else {
			whereCondition = " a.isdeleted = 'false' AND a.propstrength__status__c = 'Cancelled' AND a.propstrength__project__c in (" + finalProjectid + ")  " + vertCondition;
		}
 
		return gson.toJson(vW_OfferWithBalanceService.getOfferDtl(whereCondition));
	}
	
}