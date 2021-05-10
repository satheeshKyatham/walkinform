package com.godrej.properties.webservices;

import java.sql.Timestamp;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.CostsheetReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class CostsheetReportController {
	
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private CostsheetReportService costsheetReportService;
	
	
	@RequestMapping(value = "/getCostSheetLogReport", method = RequestMethod.POST)
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid, 
			@RequestParam("towerCode") String towerCode,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		
		String [] multiProjectid= towerCode.split(",");
		
		StringBuilder modifiedProid = new StringBuilder();
		String finalProjectid = "";
		
		for (int i=0;i<multiProjectid.length;i++){
			modifiedProid.append("'"+multiProjectid[i]+"'");
			modifiedProid.append(",");
		}
		
		finalProjectid = modifiedProid.toString();
		
		if (finalProjectid != null && finalProjectid.length() > 0 && finalProjectid.charAt(finalProjectid.length() - 1) == ',') {
			finalProjectid = finalProjectid.substring(0, finalProjectid.length() - 1);
		} 
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String whereCondition = "";
		
		if(towerCode != null && towerCode.length()>0 && (fromDate != null && fromDate.length() > 0) && (toDate != null && toDate.length() > 0)) {
			whereCondition = " a.tower_sfid in ("+finalProjectid+") AND Date(a.createddate) between '"+fromDate+"' AND '"+toDate+"' ";
		} else {
			return gson.toJson(null);
		}
		 
		return gson.toJson(costsheetReportService.getReportDtl(whereCondition));
	}
	
}