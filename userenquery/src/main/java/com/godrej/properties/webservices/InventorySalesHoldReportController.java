package com.godrej.properties.webservices;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.model.InventorySalesHoldReport;
import com.godrej.properties.service.InventorySalesHoldReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class InventorySalesHoldReportController {
	static Logger logger = Logger.getLogger(CreateOffer.class);
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private InventorySalesHoldReportService inventorySalesHoldReportService;
	
	@RequestMapping(value = { "/holdSalesExistReport" }, method = RequestMethod.POST)
	public String holdExistData (@RequestParam("projectSFID") String projectSFID, @RequestParam("towerCode") String towerCode) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		String whereCondition = "";
		
		if(projectSFID!=null && projectSFID.length()>0  && towerCode != null && towerCode.length()>0 && !towerCode.equals("All")) {
			whereCondition = " a.project_id= '"+projectSFID+"' AND b.tower_code__c = '"+towerCode+"' AND a.holdstatusyn = 'Y' AND a.statusai = 'A' ";
		} else if (projectSFID!=null && projectSFID.length()>0  && towerCode != null && towerCode.length()>0 && towerCode.equals("All") ) {
			whereCondition = " a.project_id= '"+projectSFID+"' AND a.holdstatusyn = 'Y' AND a.statusai = 'A' ";
		}
		
		try {
			List<InventorySalesHoldReport> plans = inventorySalesHoldReportService.holdDataExist(whereCondition);
			
			ArrayList<InventorySalesHoldReport> intList = new ArrayList<>(); 
			
			if (plans != null) {
				
				for(int k=0;k<plans.size();k++) {
					
					java.util.Date date = new java.util.Date();
		    		Timestamp currentTpm = new Timestamp(System.currentTimeMillis());

		    		Calendar cal = Calendar.getInstance();
		    		cal.setTimeInMillis(currentTpm.getTime());

		    		// add a bunch of seconds to the calendar
		    		cal.add(Calendar.SECOND, 98765);

		    		// create a second time stamp
		    		Timestamp timestampValue = new Timestamp(plans.get(k).getCreated_at().getTime()+ 5*60*1000);

		    		long milliseconds = timestampValue.getTime() - currentTpm.getTime();
		    		int seconds = (int) milliseconds / 1000;

		    		int hours = seconds / 3600;
		    		int minutes = (seconds % 3600) / 60;
		    		seconds = (seconds % 3600) % 60;
					
					plans.get(k).setHoldMin(minutes);
					plans.get(k).setHoldSec(seconds);
					
					intList.add(plans.get(k));
				}
				
				return gson.toJson(intList);
			} else {
				return gson.toJson("");
			}
		}catch (Exception e) {
			logger.info("Exception while get records of sales manager hold - Show on Inventory Admin Screen");
			return gson.toJson("");
		}
	}
}