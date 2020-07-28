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
	public String getEOIReportDtl(@RequestParam("projectSfid") String projectSfid, @RequestParam("towerCode") String towerCode) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String whereCondition = "";
		
		if(projectSfid!=null && projectSfid.length()>0  && towerCode != null && towerCode.length()>0 && !towerCode.equals("All")) {
			whereCondition = " a.propstrength__project_name__c= '"+projectSfid+"' AND a.tower_code__c = '"+towerCode+"'";
		} else if (projectSfid!=null && projectSfid.length()>0  && towerCode != null && towerCode.length()>0 && towerCode.equals("All") ) {
			whereCondition = " a.propstrength__project_name__c= '"+projectSfid+"' ";
		}
		
		return gson.toJson(allUnitDataService.getInventoryReportDtl(whereCondition));
	}
	
}