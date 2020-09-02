package com.godrej.properties.webservices;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.AllUnitDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="*")
@RestController
public class AllUnitDataController {
	
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private AllUnitDataService allUnitDataService;
	
	
	@RequestMapping(value = "/getAllInventoryReport", method = RequestMethod.POST)
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid, @RequestParam("towerCode") String towerCode, @RequestParam("status") String status) {
		
		
		// For multiple project report
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
		// END For multiple project report
		
		
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String whereCondition = "";
		String statusCondition = "";
		if (status.equals("All")) {
			statusCondition = "";
		} 
		
		/*else if (status.equals("HOLD")) {
			statusCondition = " AND (b.hold_status = true OR a.propstrength__property_on_hold_for_reallocation__c = true) AND a.propstrength__property_alloted_through_offer__c = false AND a.propstrength__allotted__c = false";
		}*/
		
		else if (status.equals("SOLD")) {
			statusCondition = " AND a.propstrength__property_alloted_through_offer__c = true  AND a.propstrength__allotted__c = true ";
		} else if (status.equals("AVAILABLE")) {
			statusCondition = " AND a.propstrength__property_alloted_through_offer__c = false  AND a.propstrength__allotted__c = false   ";
		} else if (status.equals("OFFERED")) {
			statusCondition = " AND a.propstrength__property_alloted_through_offer__c = true   AND a.propstrength__allotted__c = false  AND a.propstrength__property_on_hold_for_reallocation__c = false ";
		} else if (status.equals("OFFEREDSFDCHOLD")) {
			statusCondition = " AND a.propstrength__property_on_hold_for_reallocation__c = true   AND a.propstrength__property_alloted_through_offer__c = true AND a.propstrength__allotted__c = false  ";
		}
		
		
		
		if(towerCode != null && towerCode.length()>0) {
			whereCondition = " a.propstrength__tower__c in ("+finalProjectid+")" + statusCondition;
		} else {
			return gson.toJson(null);
		}
		
		/*if(projectSfid!=null && projectSfid.length()>0  && towerCode != null && towerCode.length()>0 && !towerCode.equals("All")) {
			whereCondition = " a.propstrength__project_name__c= '"+projectSfid+"' AND a.tower_code__c = '"+towerCode+"'";
		} else if (projectSfid!=null && projectSfid.length()>0  && towerCode != null && towerCode.length()>0 && towerCode.equals("All") ) {
			whereCondition = " a.propstrength__project_name__c= '"+projectSfid+"' ";
		}*/
		
		return gson.toJson(allUnitDataService.getInventoryReportDtl(whereCondition));
	}
	
}