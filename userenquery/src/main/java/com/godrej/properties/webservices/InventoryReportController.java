package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.InventoryReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class InventoryReportController {
	
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private InventoryReportService inventoryReportService;
	
	
	@RequestMapping(value = "/getInventoryReport", method = RequestMethod.GET, produces = "application/json")
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid, @RequestParam("towerCode") String towerCode) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
		String whereCondition = "";
		
		if(projectSfid!=null && projectSfid.length()>0  && towerCode != null && towerCode.length()>0 && !towerCode.equals("All")) {
			whereCondition = " a.project_id= '"+projectSfid+"' AND b.tower_code__c = '"+towerCode+"'";
		} else if (projectSfid!=null && projectSfid.length()>0  && towerCode != null && towerCode.length()>0 && towerCode.equals("All") ) {
			whereCondition = " a.project_id= '"+projectSfid+"' ";
		}
		
		return gson.toJson(inventoryReportService.getInventoryReportDtl(whereCondition));
	}
	
}