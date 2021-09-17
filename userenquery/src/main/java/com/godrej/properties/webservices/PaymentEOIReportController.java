package com.godrej.properties.webservices;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.CategoryTowerCount;
import com.godrej.properties.model.UnitCategoryCount;
import com.godrej.properties.model.UnitFacingCount;
import com.godrej.properties.model.UnitTowerCount;
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
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";
		
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
			vertCondition = " and verticle__c in ("+finalVerticales+")  ";
		} else {
			vertCondition= "";
		}
		
		
		if((fromDate!=null && fromDate.length()>0) && (toDate!=null && toDate.length()>0)) {
			whereCondition = " project_sfid= '"+projectSfid+"' and Date(date_of_eoi__c) between '"+fromDate+"' and '"+toDate+"' "+vertCondition+" order by date_of_eoi__c desc, enq_name  ";
		} else if(projectSfid!=null && projectSfid.length()>0) {
			whereCondition = " project_sfid= '"+projectSfid+"' "+vertCondition+" order by date_of_eoi__c desc, enq_name  ";
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
	public String getAllotmentDayReport(@RequestParam("projectSfid") String projectSfid, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("userVerticals") String userVerticals) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
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
			vertCondition = " and verticle__c in ("+finalVerticales+")  ";
		} else {
			vertCondition= "";
		}
		
		
		//for multiple project
		String finalProjectid = "";
		if (projectSfid != null  && !projectSfid.equals("null") && !projectSfid.equals("")) {
			// For multiple project report
			String[] multiProjectid = projectSfid.split(",");
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
		//END for multiple project
		
		
		//String whereCondition = " project_sfid in (" + finalProjectid + ") and Date(offer_date__c) between '"+fromDate+"' and '"+toDate+"' "+vertCondition+" order by offer_date__c desc  ";
		String whereCondition = "  in (" + finalProjectid + ") and Date(offer_date__c) between '"+fromDate+"' and '"+toDate+"' "+vertCondition+" ";
		
		return gson.toJson(eOIReportService.getAllotmentReport(whereCondition));
	}
	
	@GetMapping(value = "/getAllotmentDayMISReport", produces = "application/json")
	public String getAllotmentDayMISReport(@RequestParam("projectSfid") String projectSfid, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		//String whereCondition = " propstrength__project__c= '"+projectSfid+"'  ";
		return gson.toJson(eOIReportService.getAllotmentMISReport(projectSfid,fromDate,toDate));
	}
	
	@GetMapping(value = "/getTowerdashboard", produces = "application/json")
	public String getTowerdashboard(@RequestParam("projectSfid") String projectSfid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(eOIReportService.getTowerdashboard(projectSfid));
	}
	
	@GetMapping(value = "/getFacingdashboard", produces = "application/json")
	public String getFacingdashboard(@RequestParam("projectSfid") String projectSfid) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		return gson.toJson(eOIReportService.getFacingdashboard(projectSfid));
	}
	
	
	@GetMapping(value = "/getUnitFacingCount", produces = "application/json")
	public String getUnitFacingCount(@RequestParam("projectSFID") String projectSFID) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		List<UnitFacingCount> carparkTypeList=eOIReportService.getUnitFacingCount(projectSFID);
		
		return gson.toJson(carparkTypeList); 
	}
	
	
	@GetMapping(value = "/getUnitTowerCount", produces = "application/json")
	public String getUnitTowerCount(@RequestParam("projectSFID") String projectSFID) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		List<UnitTowerCount> carparkTypeList=eOIReportService.getUnitTowerCount(projectSFID);
		
		return gson.toJson(carparkTypeList); 
	}
	
	
	
	@GetMapping(value = "/getUnitCategoryCount", produces = "application/json")
	public String getUnitCategoryCount(@RequestParam("projectSFID") String projectSFID) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		List<UnitCategoryCount> carparkTypeList=eOIReportService.getUnitCategoryCount(projectSFID);
		
		return gson.toJson(carparkTypeList); 
	}
	
	@GetMapping(value = "/getCategoryTowerCount", produces = "application/json")
	public String getCategoryTowerCount(@RequestParam("projectSFID") String projectSFID) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		
		List<CategoryTowerCount> carparkTypeList=eOIReportService.getCategoryTowerCount(projectSFID);
		
		return gson.toJson(carparkTypeList); 
	}
	
}